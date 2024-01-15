@file:Suppress("DEPRECATION", "MemberVisibilityCanBePrivate")

package com.jarvis.weatherj.presentation.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.jarvis.kmm.dto.AstroHomeDto
import com.jarvis.kmm.dto.BannerHomeDto
import com.jarvis.kmm.dto.HomeDto
import com.jarvis.kmm.dto.IndexHomeDto
import com.jarvis.kmm.dto.SunHomeDto
import com.jarvis.kmm.dto.WeatherHourDto
import com.jarvis.kmm.viewmodel.WeatherViewModel
import com.jarvis.weatherj.common.click
import com.jarvis.weatherj.databinding.FragmentHomeBinding
import com.jarvis.weatherj.presentation.base.BaseFragment
import com.jarvis.weatherj.presentation.common.DataUtils
import com.jarvis.weatherj.presentation.common.FireBaseEventNameConstants
import com.jarvis.weatherj.presentation.common.FireBaseLogEvents
import com.jarvis.weatherj.presentation.common.KmmMappingHelper
import com.jarvis.weatherj.presentation.common.pref.AppPreference
import com.jarvis.weatherj.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import dev.icerock.moko.mvvm.getViewModel
import java.util.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private lateinit var mainActivity: MainActivity
    private val viewModel: WeatherViewModel by viewModels()
    override fun setUpViews() {
        binding.lifecycleOwner = this
        initData()
        binding.activityLoading.isVisible = true
        mainActivity.getLocation {
            handleLoadData(it)
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
        viewModel.getWeatherLocation(
            location = city,
            onSuccess = {
                binding.refreshData.isRefreshing = false
                binding.noInternet.isVisible = false
                binding.activityLoading.isVisible = false
                updateView(it)
            },
            onFail = {
                binding.refreshData.isRefreshing = false
                binding.noInternet.isVisible = true
                binding.activityLoading.isVisible = false
            }
        )
    }

    fun startLocationPermissionRequest() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    private fun handleRefreshView() {

        binding.refreshData.setOnRefreshListener {
            FireBaseLogEvents.getInstance()?.log(FireBaseEventNameConstants.REFRESH_DATA)
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
            FireBaseLogEvents.getInstance()?.log(FireBaseEventNameConstants.CLICK_SETTING)
        }
    }

    override fun onResume() {
        super.onResume()
        checkGPSConnection()
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
    private fun updateView(dataModel: HomeDto?) {
        updateViewBanner(dataModel?.bannerHomeDto)
        updateViewSun(dataModel?.sunHomeDto)
        updateViewIndex(dataModel?.indexHomeDto)
        updateViewGraphWeather(dataModel?.listDataWeatherHourly)
        updateViewAstro(dataModel?.astroHomeDto)
    }

    private fun updateViewBanner(dataModel: BannerHomeDto?) {
        val imageWeather = DataUtils.convertImageWeather(
            dataModel?.ivTempCurrentCode ?: 0
        )
        val addresses: List<Address>?
        val geocoder = Geocoder(mainActivity, Locale.getDefault())

        addresses = geocoder.getFromLocation(
            dataModel?.latitude ?: 0.0,
            dataModel?.longitude ?: 0.0,
            1
        )

        val address = addresses!![0].subAdminArea + ", " +
                addresses[0].adminArea

        binding.viewTop.tvPlace.text = address
        binding.viewTop.tvFeel.text = dataModel?.tvFeel?.toString(mainActivity)
        binding.viewTop.tvTemp.text = dataModel?.tvTemp?.toString(mainActivity)
        binding.viewTop.ivTempCurrent.setImageResource(imageWeather)
        binding.viewTop.tvTitle.text = dataModel?.tvTitle?.toString(mainActivity)
        binding.viewTop.tvMinMaxTempCurrent.text = dataModel?.tvMinMaxTempCurrent
        binding.viewTop.tvTimeCurrent.text = dataModel?.tvTimeCurrent
    }

    private fun updateViewAstro(dataModel: AstroHomeDto?) {
        binding.viewCurrent.tvDataMoonIllu.text = dataModel?.tvDataMoonIllu?.toString(mainActivity)
        binding.viewCurrent.tvDataMoonPhase.text = dataModel?.tvDataMoonPhase?.toString(mainActivity)
        binding.viewCurrent.tvDataVector.text = dataModel?.tvDataVector?.toString(mainActivity)
        binding.viewCurrent.tvDataVisibility.text = dataModel?.tvDataVisibility?.toString(mainActivity)
        binding.viewCurrent.tvDataCloudcover.text = dataModel?.tvDataCloudcover?.toString(mainActivity)
    }

    private fun updateViewGraphWeather(dataModel: List<WeatherHourDto>?) {
        dataModel?.map { KmmMappingHelper().toModel(it) }?.let { binding.viewCurrent.graph.drawLineChart(it) }
    }

    @SuppressLint("SetTextI18n")
    private fun updateViewIndex(dataModel: IndexHomeDto?) {
        binding.viewCurrent.tvDataUV.text = "${dataModel?.indexUV}(${dataModel?.tvDataUV?.toString(mainActivity)})"
        binding.viewCurrent.tvDataHum.text = dataModel?.tvDataHum?.toString(mainActivity)
        binding.viewCurrent.tvDataWind.text = dataModel?.tvDataWind?.toString(mainActivity)
    }

    private fun updateViewSun(dataModel: SunHomeDto?) {
        binding.viewCurrent.tvTimeSunRise.text = dataModel?.tvTimeSunRise
        binding.viewCurrent.tvTimeSunSet.text = dataModel?.tvTimeSunSet
    }
}


