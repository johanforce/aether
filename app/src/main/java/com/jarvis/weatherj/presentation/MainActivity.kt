package com.jarvis.weatherj.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import androidx.activity.viewModels
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.jarvis.design_system.item.JxListItem
import com.jarvis.locale_helper.helper.LocaleHelper
import com.jarvis.weatherj.R
import com.jarvis.weatherj.common.click
import com.jarvis.weatherj.common.observe
import com.jarvis.weatherj.databinding.ActivityMainBinding
import com.jarvis.weatherj.presentation.base.BaseActivity
import com.jarvis.weatherj.presentation.bmiother.DetailTodayFragment
import com.jarvis.weatherj.presentation.common.Constant
import com.jarvis.weatherj.presentation.common.ThemeMode
import com.jarvis.weatherj.presentation.common.pref.AppPreferenceKey
import com.jarvis.weatherj.presentation.home.HomeFragment
import com.jarvis.weatherj.presentation.profile.ProfileFragment
import com.jarvis.weatherj.presentation.selectmode.SelectModeActivity
import com.jarvis.weatherj.presentation.week.WeekFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate) {

    private var isBackPress = false
    private var homeFragment: HomeFragment? = null
    private var profileFragment: ProfileFragment? = null
    private var weekFragment: WeekFragment? = null
    private var detailTodayFragment: DetailTodayFragment? = null
    private val fragments: MutableList<Fragment> = arrayListOf()
    private var currentIndex: Int = 0

    var viewDarkMode: JxListItem? = null
    var viewAbout: JxListItem? = null
    var viewLanguage: JxListItem? = null
    var viewVersion: JxListItem? = null
    var viewUnit: JxListItem? = null

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.splash.visibility = View.VISIBLE
        setupSlidingDrawer()
    }

    override fun setUpViews() {
        initViewSubMenu()
        initFragment()
        clickShowFragment(Constant.KEY_HOME)
        setOnClickView()
    }

    private fun initViewSubMenu() {
        viewDarkMode = findViewById(R.id.viewDarkMode)
        viewAbout = findViewById(R.id.viewAbout)
        viewLanguage = findViewById(R.id.viewLanguage)
        viewVersion = findViewById(R.id.viewVersion)
        viewUnit = findViewById(R.id.viewUnit)

        initLanguage()
        initDarkModeMenu()
    }

    private fun initDarkModeMenu() {
        val themeMode = appPreference?.get(AppPreferenceKey.KEY_DARKMODE, Int::class.java)
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

    fun hideSplash() {
        binding.splash.visibility = View.GONE
    }

    private fun setOnClickView() {
        val clDarkMode = findViewById<View>(R.id.clDarkMode)
        val viewAbout = findViewById<View>(R.id.viewAbout)
        val viewLanguage = findViewById<View>(R.id.viewLanguage)
        val viewVersion = findViewById<View>(R.id.viewVersion)
        val viewUnit = findViewById<View>(R.id.viewUnit)

        clDarkMode.click {
            handleDarkMode()
        }

        viewLanguage.click {
            handleLanguage()
        }

        viewUnit.click {
            handleUnit()
        }
    }

    private fun handleUnit() {

    }

    private fun handleLanguage() {
        LocaleHelper.getInstance()
            .changeLanguage(
                this, localeDelegate
            ) {
                appPreference?.put(AppPreferenceKey.KEY_IS_CHANGE_LANGUAGE, true)
                appPreference?.put(
                    AppPreferenceKey.KEY_LOCALE_SETTING,
                    Locale.getDefault().toString()
                )
                initLanguage()
                restartActivity()
            }
    }

    private fun handleDarkMode() {
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
        profileFragment = ProfileFragment()
        weekFragment = WeekFragment()
        detailTodayFragment = DetailTodayFragment()

        fragments.add(homeFragment ?: HomeFragment())
        fragments.add(profileFragment ?: ProfileFragment())
        fragments.add(weekFragment ?: WeekFragment())
        fragments.add(detailTodayFragment ?: DetailTodayFragment())

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

    override fun onBackPressed() {
        if (this.currentIndex != Constant.KEY_HOME) {
            this.viewModel.tempFrag.value = 1
            clickShowFragment(Constant.KEY_HOME)
            return
        }
        if (!isBackPress) {
            isBackPress = true
            Handler(Looper.getMainLooper()).postDelayed({
                isBackPress = false
            }, 2000)
            return
        }
        super.onBackPressed()
    }
}
