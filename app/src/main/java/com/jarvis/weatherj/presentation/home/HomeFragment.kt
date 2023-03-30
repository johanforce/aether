@file:Suppress("DEPRECATION")

package com.jarvis.weatherj.presentation.home

import android.Manifest
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.jarvis.weatherj.R
import com.jarvis.weatherj.common.click
import com.jarvis.weatherj.common.observe
import com.jarvis.weatherj.databinding.FragmentHomeBinding
import com.jarvis.weatherj.domain.model.model.demo.DataModel
import com.jarvis.weatherj.presentation.base.BaseFragment
import com.jarvis.weatherj.presentation.common.DataUtils
import com.jarvis.weatherj.presentation.common.DataUtils.toTimeShowUI
import com.jarvis.weatherj.presentation.common.FireBaseEventNameConstants
import com.jarvis.weatherj.presentation.common.FireBaseLogEvents
import com.jarvis.weatherj.presentation.common.pref.AppPreference
import com.jarvis.weatherj.presentation.common.pref.AppPreferenceKey
import com.jarvis.weatherj.presentation.main.MainActivity
import com.jarvis.weatherj.presentation.pref.AppPrefs
import com.jarvis.weatherj.presentation.pref.SharedPrefsKey
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var mainActivity: MainActivity
    override fun setUpViews() {
        binding.lifecycleOwner = this

        initData()

        if (!isNetworkAvailable()) {
            val dataPref = AppPrefs.getOrNull(SharedPrefsKey.KEY_PREF_DATA, DataModel::class.java)
            if (dataPref == null)
                binding.noInternet.isVisible = true
            else
                viewModel.dataWeather.value = dataPref
            viewModel.mLoading.value = false
        } else {
            mainActivity.initGPSLocation {
                handleLoadData(it)
            }
        }
    }

    private fun initData() {
        mainActivity = activity as MainActivity
        appPreference = AppPreference.getInstance()
        handleRefreshView()
    }

    var requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            mainActivity.getLocation { city ->
                handleLoadData(city)
            }
        } else {
            Toast.makeText(
                activity,
                "Location is Required: Please enable location from Settings",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun handleLoadData(city: String) {
        viewModel.loadDataWeather(city)
    }

    fun startLocationPermissionRequest() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    private fun handleRefreshView() {

        binding.refreshData.setOnRefreshListener {
            FireBaseLogEvents.getInstance().log(FireBaseEventNameConstants.REFRESH_DATA)
            when {
                !isNetworkAvailable() -> {
                    binding.refreshData.isRefreshing = false
                    binding.noInternet.isVisible = true
                }
                else -> {
                    val locationManager =
                        mainActivity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        mainActivity.showAlertMessageLocationDisabled()
                    } else
                        mainActivity.getLocation {
                            handleLoadData(it)
                        }
                }
            }
        }

        binding.viewTop.ivMore.click {
            (activity as? MainActivity)?.setMenuOpenStatus(true)
            FireBaseLogEvents.getInstance().log(FireBaseEventNameConstants.CLICK_SETTING)
        }
    }

    override fun observeData() {
        observe(viewModel.dataWeather) {
            binding.noInternet.isVisible = false
            updateView(it)
            appPreference?.put(AppPreferenceKey.KEY_DATA, it)
            appPreference?.put(
                AppPreferenceKey.KEY_TIME_LAST_LOAD_DATA,
                System.currentTimeMillis()
            )
        }

        observe(viewModel.mLoading) {
            binding.activityLoading.isVisible = it
            if (it != false)
                binding.refreshData.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
//        checkGPSConnection()
    }

    private fun checkGPSConnection() {
        val locationManager =
            mainActivity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            mainActivity.showAlertMessageLocationDisabled()
        }
    }

    /**
     * Handle update data to view
     */
    private fun updateView(dataModel: DataModel) {
        updateViewBanner(dataModel)
        updateViewSun(dataModel)
        updateViewIndex(dataModel)
        updateViewGraphWeather(dataModel)
        updateViewAstro(dataModel)
    }

    private fun updateViewBanner(dataModel: DataModel) {
        val statusWeather = DataUtils.convertTitleWeather(
            dataModel.current_condition?.get(0)?.weatherCode?.toInt() ?: 0
        )
        val feelLike = dataModel.current_condition?.get(0)?.FeelsLikeC

        val tempCurrent =
            getString(R.string.current_temp, dataModel.current_condition?.get(0)?.temp_C)
        val imageWeather = DataUtils.convertImageWeather(
            dataModel.current_condition?.get(0)?.weatherCode?.toInt() ?: 0
        )

        val addresses: List<Address>?
        val geocoder = Geocoder(mainActivity, Locale.getDefault())

        addresses = geocoder.getFromLocation(
            dataModel.nearest_area?.get(0)?.latitude?.toDouble() ?: 0.0,
            dataModel.nearest_area?.get(0)?.longitude?.toDouble() ?: 0.0,
            1
        )

        val address = addresses!![0].subAdminArea + ", " +
                addresses[0].adminArea

        binding.viewTop.tvPlace.text = address
        binding.viewTop.tvTemp.text = tempCurrent
        binding.viewTop.ivTempCurrent.setImageResource(imageWeather)
        binding.viewTop.tvTitle.text = getString(statusWeather)
        binding.viewTop.tvFeel.text = getString(R.string.feel_like, feelLike)
        binding.viewTop.tvMinMaxTempCurrent.text = getString(
            R.string.min_max_temp,
            dataModel.weather?.get(0)?.maxtempC,
            dataModel.weather?.get(0)?.mintempC
        )
        binding.viewTop.tvTimeCurrent.text =
            AppPrefs.getLong(SharedPrefsKey.KEY_PREF_DATA_TIME)
                .toTimeShowUI(DataUtils.ISO_8601_DATE_TIME_FORMAT)
    }

    private fun updateViewAstro(dataModel: DataModel) {
        val visibility = dataModel.current_condition?.get(0)?.visibility
        val cloudcover = dataModel.current_condition?.get(0)?.cloudcover
        val moonIllu = dataModel.weather?.get(0)?.astronomy?.get(0)?.moonIllumination
        val moonPhase = dataModel.weather?.get(0)?.astronomy?.get(0)?.moonPhase
        val windDir = DataUtils.convertWindDirToWind(
            dataModel.current_condition?.get(0)?.winddir16Point ?: ""
        )

        binding.viewCurrent.tvDataMoonIllu.text = getString(R.string.percent_index, moonIllu)
        binding.viewCurrent.tvDataMoonPhase.text = moonPhase
        binding.viewCurrent.tvDataVector.text = getString(windDir)
        binding.viewCurrent.tvDataVisibility.text = getString(R.string.km_data, visibility)
        binding.viewCurrent.tvDataCloudcover.text =
            getString(R.string.percent_index, cloudcover)
    }

    private fun updateViewGraphWeather(dataModel: DataModel) {
        val listDataWeatherHourly = viewModel.getListWeatherHour(dataModel)

        binding.viewCurrent.graph.drawLineChart(listDataWeatherHourly)
    }

    private fun updateViewIndex(dataModel: DataModel) {
        val indexUV = dataModel.current_condition?.get(0)?.uvIndex
        val dataHumidity = dataModel.current_condition?.get(0)?.humidity
        val speedWind = dataModel.current_condition?.get(0)?.windspeedKmph

        binding.viewCurrent.tvDataUV.text = getString(R.string.uv_index_text, indexUV,
            context?.let { DataUtils.convertIndexUV(it, indexUV ?: "") })

        binding.viewCurrent.tvDataHum.text = getString(R.string.percent_index, dataHumidity)
        binding.viewCurrent.tvDataWind.text = getString(R.string.speed_data, speedWind)
    }

    private fun updateViewSun(dataModel: DataModel) {
        val sunRise = dataModel.weather?.get(0)?.astronomy?.get(0)?.sunrise
        val sunSet = dataModel.weather?.get(0)?.astronomy?.get(0)?.sunset

        binding.viewCurrent.tvTimeSunRise.text = sunRise
        binding.viewCurrent.tvTimeSunSet.text = sunSet
    }
}


