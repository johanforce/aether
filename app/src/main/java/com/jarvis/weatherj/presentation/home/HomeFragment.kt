package com.jarvis.weatherj.presentation.home

import androidx.fragment.app.viewModels
import com.jarvis.weatherj.R
import com.jarvis.weatherj.common.click
import com.jarvis.weatherj.common.observe
import com.jarvis.weatherj.databinding.FragmentHomeBinding
import com.jarvis.weatherj.domain.model.model.demo.DataModel
import com.jarvis.weatherj.presentation.MainActivity
import com.jarvis.weatherj.presentation.base.BaseFragment
import com.jarvis.weatherj.presentation.common.DataUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun setUpViews() {
        binding.lifecycleOwner = this

        initData()
        setOnClickView()
    }

    private fun initData() {
        viewModel.loadDataWeather()
    }

    private fun setOnClickView() {

    }

    override fun observeData() {
        observe(viewModel.dataWeather) {
            (activity as? MainActivity)?.hideSplash()
            updateView(it)
        }
    }

    private fun updateView(dataModel: DataModel) {
        val dataHumidity = dataModel.current_condition?.get(0)?.humidity
        val speedWind = dataModel.current_condition?.get(0)?.windspeedKmph
        val moonIllu = dataModel.weather?.get(0)?.astronomy?.get(0)?.moon_illumination
        val moonPhase = dataModel.weather?.get(0)?.astronomy?.get(0)?.moon_phase
        val windDir = DataUtils.convertWindDirToWind(
            dataModel.current_condition?.get(0)?.winddir16Point ?: ""
        )
        val sunRise = dataModel.weather?.get(0)?.astronomy?.get(0)?.sunrise
        val sunSet = dataModel.weather?.get(0)?.astronomy?.get(0)?.sunset
        val feelLike = dataModel.current_condition?.get(0)?.FeelsLikeC

        val visibility = dataModel.current_condition?.get(0)?.visibility
        val cloudcover = dataModel.current_condition?.get(0)?.cloudcover
        val indexUV = dataModel.current_condition?.get(0)?.uvIndex

        binding.viewTop.tvPlace.text =
            dataModel.nearest_area?.get(0)?.areaName?.get(0)?.value + ", " + dataModel.nearest_area?.get(
                0
            )?.country?.get(0)?.value

        binding.viewTop.tvTemp.text =
            getString(R.string.current_temp, dataModel.current_condition?.get(0)?.temp_C)

        binding.viewTop.tvMinMaxTempCurrent.text = getString(
            R.string.min_max_temp,
            dataModel.weather?.get(0)?.maxtempC,
            dataModel.weather?.get(0)?.mintempC
        )
        binding.viewTop.tvTimeCurrent.text = dataModel.current_condition?.get(0)?.localObsDateTime

        binding.viewCurrent.tvDataUV.text = getString(R.string.uv_index_text, indexUV,
            context?.let { DataUtils.convertIndexUV(it, indexUV ?: "") })

        binding.viewCurrent.tvDataHum.text = getString(R.string.percent_index, dataHumidity)
        binding.viewCurrent.tvDataWind.text = getString(R.string.speed_data, speedWind)

        binding.viewCurrent.tvDataMoonIllu.text = getString(R.string.percent_index, moonIllu)
        binding.viewCurrent.tvDataMoonPhase.text = moonPhase

        binding.viewCurrent.tvTimeSunRise.text = sunRise
        binding.viewCurrent.tvTimeSunSet.text = sunSet

        binding.viewCurrent.tvDataVector.text = windDir
        binding.viewCurrent.tvDataVisibility.text = getString(R.string.km_data, visibility)
        binding.viewCurrent.tvDataCloudcover.text = getString(R.string.percent_index, cloudcover)
        binding.viewTop.tvFeel.text = getString(R.string.feel_like, feelLike)

        binding.viewTop.ivMore.click {
            (activity as? MainActivity)?.setMenuOpenStatus(true)
        }
    }
}

