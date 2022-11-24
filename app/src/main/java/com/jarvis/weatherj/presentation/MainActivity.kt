package com.jarvis.weatherj.presentation

import android.os.Bundle

import androidx.activity.viewModels

import com.jarvis.weatherj.common.observe
import com.jarvis.weatherj.databinding.ActivityMainBinding
import com.jarvis.weatherj.presentation.base.BaseActivity
import com.jarvis.weatherj.presentation.base.data.StateData
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
        viewModel.loadDataWeather()
    }


    override fun observeData() {
        super.observeData()

        observe(viewModel.data){
            if(it.data.isNullOrEmpty()){
                binding.tvData.text = it.error.toString()
            }else{
                binding.tvData.text = it.data.toString()
            }
        }
    }


}
