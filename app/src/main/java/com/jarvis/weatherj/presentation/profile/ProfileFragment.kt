@file:Suppress("UNUSED_PARAMETER")

package com.jarvis.weatherj.presentation.profile

import android.app.Activity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.jarvis.weatherj.databinding.FragmentHomeBinding
import com.jarvis.weatherj.presentation.base.BaseFragment
import com.jarvis.weatherj.presentation.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@Suppress("unused")
@AndroidEntryPoint
class ProfileFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun setUpViews() {
        binding.lifecycleOwner = this

        setOnClickView()

    }

    private fun initData() {

    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }

    override fun observeData() {
        super.observeData()


    }

    private fun setOnClickView() {

    }


    private fun changeUnit(isKmSetting: Boolean) {

    }


    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                activity?.setResult(Activity.RESULT_OK, activity?.intent)
            }
        }
}


