package com.jarvis.weatherj.presentation.selectmode

import androidx.activity.viewModels
import androidx.core.view.WindowInsetsControllerCompat
import com.jarvis.design_system.toolbar.JxToolbar
import com.jarvis.weatherj.R
import com.jarvis.weatherj.common.click
import com.jarvis.weatherj.common.observe
import com.jarvis.weatherj.databinding.ActivitySelectModeBinding
import com.jarvis.weatherj.presentation.base.BaseActivity
import com.jarvis.weatherj.presentation.common.ThemeHelper
import com.jarvis.weatherj.presentation.common.ThemeMode
import com.jarvis.weatherj.presentation.common.pref.AppPreferenceKey
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectModeActivity :
    BaseActivity<ActivitySelectModeBinding, SelectModeViewModel>(ActivitySelectModeBinding::inflate) {

    private val viewModel: SelectModeViewModel by viewModels()

    override fun initDarkMode() {
        setTheme(R.style.Theme_WeatherJ)
    }

    override fun setUpViews() {
        super.setUpViews()
        binding.lifecycleOwner = this
        setOnClickView()
        viewModel.themeMode.value = appPreference?.get(AppPreferenceKey.KEY_DARKMODE, Int::class.java)
    }

    private fun setOnClickView() {
        binding.viewSelectLightMode.click {
            viewModel.selectMode(ThemeMode.LIGHT.index)
        }

        binding.viewSelectDarkMode.click {
            viewModel.selectMode(ThemeMode.DARK.index)
        }

        binding.viewSelectSystemMode.click {
            viewModel.selectMode(ThemeMode.FOLLOW_SYSTEM.index)
        }
    }

    override fun observeData() {
        super.observeData()

        observe(viewModel.themeMode) { mode ->
            updateSelectModeButton(mode)
            appPreference?.putSync(AppPreferenceKey.KEY_DARKMODE, mode)
        }
    }

    private fun updateSelectModeButton(index: Int) {
        when (index) {
            ThemeMode.LIGHT.index -> {
                binding.viewSelectLightMode.viewBinder.imageViewStartIcon.isSelected = true
                binding.viewSelectDarkMode.viewBinder.imageViewStartIcon.isSelected = false
                binding.viewSelectSystemMode.viewBinder.imageViewStartIcon.isSelected = false
                WindowInsetsControllerCompat(
                    window,
                    binding.viewParent
                ).isAppearanceLightStatusBars = true
            }
            ThemeMode.DARK.index -> {
                binding.viewSelectLightMode.viewBinder.imageViewStartIcon.isSelected = false
                binding.viewSelectDarkMode.viewBinder.imageViewStartIcon.isSelected = true
                binding.viewSelectSystemMode.viewBinder.imageViewStartIcon.isSelected = false
                WindowInsetsControllerCompat(
                    window,
                    binding.viewParent
                ).isAppearanceLightStatusBars = false
            }
            ThemeMode.FOLLOW_SYSTEM.index -> {
                binding.viewSelectLightMode.viewBinder.imageViewStartIcon.isSelected = false
                binding.viewSelectDarkMode.viewBinder.imageViewStartIcon.isSelected = false
                binding.viewSelectSystemMode.viewBinder.imageViewStartIcon.isSelected = true
                WindowInsetsControllerCompat(
                    window,
                    binding.viewParent
                ).isAppearanceLightStatusBars = !isDarkTheme()
            }
        }
        ThemeHelper.applyTheme(index)
    }

    override fun getToolbar(): JxToolbar {
        return binding.toolbar
    }

}
