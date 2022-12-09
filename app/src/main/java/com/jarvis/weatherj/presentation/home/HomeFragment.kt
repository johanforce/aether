package com.jarvis.weatherj.presentation.home

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.jarvis.weatherj.R
import com.jarvis.weatherj.common.LOADING
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
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()

    private var isLoadData = false
    override fun setUpViews() {
        binding.lifecycleOwner = this

        initData()
        if (!isNetworkAvailable() && !isLoadData) {
            binding.noInternet.isVisible = true
        } else {
            reloadData()
        }
    }

    private fun initData() {
        appPreference = AppPreference.getInstance()
        handleRefreshView()
    }

    private fun handleRefreshView() {
        binding.refreshData.setOnRefreshListener {
            FireBaseLogEvents.getInstance().log(FireBaseEventNameConstants.REFRESH_DATA)
            if (!isNetworkAvailable() && !isLoadData) {
                binding.refreshData.isRefreshing = false
                binding.noInternet.isVisible = true
            } else {
                reloadData()
            }
        }

        binding.viewTop.ivMore.click {
            (activity as? MainActivity)?.setMenuOpenStatus(true)
            FireBaseLogEvents.getInstance().log(FireBaseEventNameConstants.CLICK_SETTING)
        }
    }

    /**
     * Function reload data
     */
    private fun reloadData() {
        binding.noInternet.isVisible = false
        val lastTime =
            appPreference?.get(AppPreferenceKey.KEY_TIME_LAST_LOAD_DATA, Long::class.java) ?: 0L
        val totalTime = System.currentTimeMillis() - lastTime
        if (totalTime >= 60 * 60 * 1000 && isNetworkAvailable()) {
            binding.refreshData.isRefreshing = false
            viewModel.loadDataWeather()
        } else {
            viewModel.dataWeather.value =
                appPreference?.get(AppPreferenceKey.KEY_DATA, DataModel::class.java)
            viewModel.mLoading.value = LOADING.END
        }

    }


    override fun observeData() {
        observe(viewModel.dataWeather) {
            isLoadData = true
            binding.noInternet.isVisible = false
            updateView(it)
            appPreference?.put(AppPreferenceKey.KEY_DATA, it)
            appPreference?.put(AppPreferenceKey.KEY_TIME_LAST_LOAD_DATA, System.currentTimeMillis())
        }

        observe(viewModel.mLoading) {
            binding.activityLoading.isVisible = it == LOADING.START
            if (it != LOADING.START)
                binding.refreshData.isRefreshing = false
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
        val place =
            dataModel.nearest_area?.get(0)?.areaName?.get(0)?.value + ", " + dataModel.nearest_area?.get(
                0
            )?.country?.get(0)?.value
        val tempCurrent =
            getString(R.string.current_temp, dataModel.current_condition?.get(0)?.temp_C)
        val imageWeather = DataUtils.convertImageWeather(
            dataModel.current_condition?.get(0)?.weatherCode?.toInt() ?: 0
        )

        binding.viewTop.tvPlace.text = place
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
            System.currentTimeMillis().toTimeShowUI(DataUtils.ISO_8601_DATE_TIME_FORMAT)
    }

    private fun updateViewAstro(dataModel: DataModel) {
        val visibility = dataModel.current_condition?.get(0)?.visibility
        val cloudcover = dataModel.current_condition?.get(0)?.cloudcover
        val moonIllu = dataModel.weather?.get(0)?.astronomy?.get(0)?.moon_illumination
        val moonPhase = dataModel.weather?.get(0)?.astronomy?.get(0)?.moon_phase
        val windDir = DataUtils.convertWindDirToWind(
            dataModel.current_condition?.get(0)?.winddir16Point ?: ""
        )

        binding.viewCurrent.tvDataMoonIllu.text = getString(R.string.percent_index, moonIllu)
        binding.viewCurrent.tvDataMoonPhase.text = moonPhase
        binding.viewCurrent.tvDataVector.text = getString(windDir)
        binding.viewCurrent.tvDataVisibility.text = getString(R.string.km_data, visibility)
        binding.viewCurrent.tvDataCloudcover.text = getString(R.string.percent_index, cloudcover)
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


