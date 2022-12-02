package com.jarvis.weatherj.presentation.home

import android.app.Activity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.jarvis.weatherj.R
import com.jarvis.weatherj.common.observe
import com.jarvis.weatherj.databinding.FragmentHomeBinding
import com.jarvis.weatherj.domain.model.model.demo.DataModel
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


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }

    private fun setOnClickView() {

    }

    override fun observeData() {
        observe(viewModel.dataWeather) {
            updateView(it)
        }
    }

    private fun updateView(dataModel: DataModel) {
        val dataHumidity = dataModel.current_condition?.get(0)?.humidity
        val speedWind = dataModel.current_condition?.get(0)?.windspeedKmph
        val aqiCurrent = dataModel.current_condition?.get(0)?.winddirDegree

        binding.tvPlace.text =
            dataModel.nearest_area?.get(0)?.areaName?.get(0)?.value + ", " + dataModel.nearest_area?.get(
                0
            )?.country?.get(0)?.value

        binding.tvTemp.text =
            getString(R.string.current_temp, dataModel.current_condition?.get(0)?.temp_C)

        binding.tvMinMaxTempCurrent.text = getString(
            R.string.min_max_temp,
            dataModel.weather?.get(0)?.maxtempC,
            dataModel.weather?.get(0)?.mintempC
        )
        binding.tvTimeCurrent.text = dataModel.current_condition?.get(0)?.localObsDateTime

        val indexUV = dataModel.current_condition?.get(0)?.uvIndex
        binding.viewCurrent.tvDataUV.text = getString(R.string.uv_index_text, indexUV,
            context?.let { DataUtils.convertIndexUV(it, indexUV ?: "") })

        binding.viewCurrent.tvDataHum.text = getString(R.string.percent_index, dataHumidity)
        binding.viewCurrent.tvDataWind.text = getString(R.string.speed_data, speedWind)

        binding.viewCurrent.tvDataAQI.text = getString(R.string.speed_data, aqiCurrent)

        binding.viewCurrent.tvTimeBinhMinh.text =
            dataModel.weather?.get(0)?.astronomy?.get(0)?.sunrise
        binding.viewCurrent.tvTimeHoangHon.text =
            dataModel.weather?.get(0)?.astronomy?.get(0)?.sunset
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                activity?.setResult(Activity.RESULT_OK, activity?.intent)
            }
        }
}

