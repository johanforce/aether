package com.jarvis.weatherj.presentation.bmiother

import android.app.Activity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.jarvis.weatherj.databinding.FragmentDetailTodayBinding
import com.jarvis.weatherj.databinding.FragmentHomeBinding
import com.jarvis.weatherj.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTodayFragment : BaseFragment<FragmentDetailTodayBinding>(FragmentDetailTodayBinding::inflate) {

    private val viewModel: DetailTodayViewModel by viewModels()

    override fun setUpViews() {
        binding.lifecycleOwner = this

        initData()
        setOnClickView()
    }

    private fun initData() {

    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }

    private fun setOnClickView() {

    }

    override fun observeData() {

    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                activity?.setResult(Activity.RESULT_OK, activity?.intent)
            }
        }
}

