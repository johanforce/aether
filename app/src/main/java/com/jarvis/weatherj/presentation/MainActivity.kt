package com.jarvis.weatherj.presentation

import android.os.Bundle
import androidx.activity.viewModels
import com.jarvis.weatherj.common.LOADING
import com.jarvis.weatherj.common.observe
import com.jarvis.weatherj.databinding.ActivityMainBinding
import com.jarvis.weatherj.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
    }

    override fun setUpViews() {
        binding.fab.setOnClickListener {
            viewModel.loadDataWeather()
        }
    }

    override fun observeData() {
        super.observeData()

        observe(viewModel.mError) {
            binding.tvError.text = it.toString()
        }

        observe(viewModel.mLoading) {
            if (it == LOADING.START) {
                binding.tvLoading.text = "Loading"
            } else {
                binding.tvLoading.text = "Chỗ để load data"
            }
        }

        observe(viewModel.data) {
            binding.tvData.text = it.toString()
        }
    }


}
