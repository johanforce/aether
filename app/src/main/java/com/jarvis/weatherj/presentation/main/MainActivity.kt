@file:Suppress("SameParameterValue", "DEPRECATION")

package com.jarvis.weatherj.presentation.main

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.gms.location.LocationServices
import com.jarvis.locale_helper.helper.LocaleHelper
import com.jarvis.weatherj.BuildConfig
import com.jarvis.weatherj.R
import com.jarvis.weatherj.common.click
import com.jarvis.weatherj.common.observe
import com.jarvis.weatherj.databinding.ActivityMainBinding
import com.jarvis.weatherj.presentation.base.BaseActivity
import com.jarvis.weatherj.presentation.common.Constant
import com.jarvis.weatherj.presentation.common.FireBaseEventNameConstants
import com.jarvis.weatherj.presentation.common.FireBaseLogEvents
import com.jarvis.weatherj.presentation.common.ThemeMode
import com.jarvis.weatherj.presentation.home.HomeFragment
import com.jarvis.weatherj.presentation.pref.AppPrefs
import com.jarvis.weatherj.presentation.pref.SharedPrefsKey
import com.jarvis.weatherj.presentation.selectmode.SelectModeActivity
import com.jarvis.weatherj.presentation.widget.listItem.JxListItem
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate) {

    private var isBackPress = false
    private var homeFragment: HomeFragment? = null
    private val fragments: MutableList<Fragment> = arrayListOf()
    private var currentIndex: Int = 0

    private var viewDarkMode: JxListItem? = null
    private var viewLanguage: JxListItem? = null
    private var viewVersion: JxListItem? = null

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        setupSlidingDrawer()
    }

    override fun setUpViews() {
        initViewSubMenu()
        initFragment()
        clickShowFragment(Constant.KEY_HOME)
        setOnClickView()
    }

    fun initGPSLocation(locationResult: (location: String) -> Unit) {
        val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation {
                locationResult(it)
            }
        } else {
            locationResult("")
        }
    }

    fun showAlertMessageLocationDisabled() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(getString(R.string.divice_gps_title))
        builder.setCancelable(false)
        builder.setPositiveButton(
            getString(R.string.yes)
        ) { p0, _ ->
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            p0.dismiss()
        }
        builder.setNegativeButton(getString(R.string.cancel)) { _, _ -> finish() }
        val dialog = builder.create()
        dialog.show()
    }

    @SuppressLint("InlinedApi")
    fun getLocation(locationResult: (location: String) -> Unit) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            try {
                val mLocationRequest = com.google.android.gms.location.LocationRequest.create()
                    .setPriority(LocationRequest.QUALITY_BALANCED_POWER_ACCURACY)
                    .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                    .setFastestInterval(1 * 1000)
                val fusedLocation = LocationServices.getFusedLocationProviderClient(this)
                fusedLocation.requestLocationUpdates(mLocationRequest, { location ->
                    val addresses: List<Address>?
                    val geocoder = Geocoder(this, Locale.getDefault())

                    addresses = try {
                        geocoder.getFromLocation(
                            location.latitude,
                            location.longitude,
                            1
                        )
                    } catch (e: Exception) {
                        emptyList()
                    }
                    if (addresses.isNullOrEmpty()) {
                        locationResult("")
                    } else {
                        val address = addresses[0].subAdminArea + ", " +
                                addresses[0].adminArea + ", " + addresses[0].countryName
                        AppPrefs.saveString(SharedPrefsKey.KEY_PREF_LOCATION, address)
                        locationResult(address)
                    }
                }, null)
            } catch (e: Exception) {
                locationResult("")
            }
        } else {
            requestPermissionGPS()
        }
    }

    private fun requestPermissionGPS() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            homeFragment?.startLocationPermissionRequest()
        }
    }


    private fun initViewSubMenu() {
        viewDarkMode = findViewById(R.id.viewDarkMode)
        viewLanguage = findViewById(R.id.viewLanguage)
        viewVersion = findViewById(R.id.viewVersion)

        initLanguage()
        initDarkModeMenu()
        initVersion()
    }

    private fun initVersion() {
        viewVersion?.viewBinder?.setTextValueStyle1(
            BuildConfig.VERSION_NAME
        )
    }

    private fun initDarkModeMenu() {
        val themeMode = AppPrefs.getOrNull(SharedPrefsKey.KEY_DARKMODE, Int::class.java)
        var textState = getString(R.string.all_off)
        when (themeMode) {
            ThemeMode.LIGHT.index -> {
                textState = getString(R.string.all_off)
            }
            ThemeMode.DARK.index -> {
                textState = getString(R.string.all_on)
            }
            ThemeMode.FOLLOW_SYSTEM.index -> {
                textState = getString(R.string.all_mode_system)
            }
        }
        viewDarkMode?.viewBinder?.setTextValueStyle1(textState)
    }

    private fun initLanguage() {
        viewLanguage?.viewBinder?.setTextValueStyle1(
            LocaleHelper.getInstance()
                .getCurrentDisplayLanguage(this)
        )

    }

    private fun setOnClickView() {
        val clDarkMode = findViewById<View>(R.id.clDarkMode)
        val viewLanguage = findViewById<View>(R.id.viewLanguage)

        clDarkMode.click {
            handleDarkMode()
        }

        viewLanguage.click {
            handleLanguage()
        }
    }

    private fun handleLanguage() {
        FireBaseLogEvents.getInstance()?.log(FireBaseEventNameConstants.CLICK_LANGUAGE)
        LocaleHelper.getInstance()
            .changeLanguage(
                this, localeDelegate
            ) {
                AppPrefs.save(SharedPrefsKey.KEY_IS_CHANGE_LANGUAGE, true)
                AppPrefs.save(
                    SharedPrefsKey.KEY_LOCALE_SETTING,
                    Locale.getDefault().toString()
                )
                initLanguage()
                restartActivity()
            }
    }

    private fun handleDarkMode() {
        FireBaseLogEvents.getInstance()?.log(FireBaseEventNameConstants.CLICK_DARKMODE)
        val intent = Intent(this, SelectModeActivity::class.java)
        startActivity(intent)
    }

    private fun restartActivity() {
        val intent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(intent)
    }

    private fun initFragment() {
        homeFragment = HomeFragment()
        fragments.add(homeFragment ?: HomeFragment())

        val size = fragments.size
        val supportFragmentManager = supportFragmentManager

        for (index in 0 until size) {
            val fragment = fragments[index]
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            if (!fragment.isAdded) {
                fragmentTransaction.add(R.id.frag, fragment, "fragment$index")
            }
            if (index != 0) {
                fragmentTransaction.hide(fragment).commitAllowingStateLoss()
            } else {
                fragmentTransaction.commitAllowingStateLoss()
            }
        }
    }

    @SuppressLint("CommitTransaction")
    private fun clickShowFragment(position: Int) {
        try {
            val targetFragment = fragments[position]
            val currentFragment: Fragment = getCurrentFragment()
            if (currentFragment.isAdded) {
                currentFragment.onPause()
            }
            if (!isFinishing) {
                supportFragmentManager.beginTransaction().hide(currentFragment).show(targetFragment)
                    .commitAllowingStateLoss()
                currentIndex = position
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getCurrentFragment(): Fragment {
        return fragments[currentIndex]
    }

    override fun observeData() {
        super.observeData()

        observe(viewModel.isMenuExpanded) {
            if (it) binding.drawer.open() else binding.drawer.close()
        }
    }

    private fun setupSlidingDrawer() {
        binding.drawer.apply {
            useCustomBehavior(Gravity.START)
            useCustomBehavior(Gravity.END)
            setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN)
            setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            onDrawerClosed = {
                viewModel.isMenuExpanded.value = false
            }
            onDrawerOpened = {
                viewModel.isMenuExpanded.value = true
            }
        }
    }

    fun setMenuOpenStatus(isExpanded: Boolean) {
        viewModel.isMenuExpanded.value = isExpanded
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (this.currentIndex != Constant.KEY_HOME) {
            this.viewModel.tempFrag.value = 1
            clickShowFragment(Constant.KEY_HOME)
            return
        }
        if (!isBackPress) {
            Toast.makeText(this, resources.getString(R.string.home_exit), Toast.LENGTH_SHORT).show()
            isBackPress = true
            Handler(Looper.getMainLooper()).postDelayed({
                isBackPress = false
            }, 2000)
            return
        }
        super.onBackPressed()
    }
}
