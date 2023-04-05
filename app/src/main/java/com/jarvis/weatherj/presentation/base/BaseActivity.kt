@file:Suppress("MemberVisibilityCanBePrivate", "DEPRECATION")

package com.jarvis.weatherj.presentation.base

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.jarvis.locale_helper.helper.LocaleHelperActivityDelegateImpl
import com.jarvis.weatherj.R
import com.jarvis.weatherj.presentation.common.ThemeHelper
import com.jarvis.weatherj.presentation.pref.AppPrefs
import com.jarvis.weatherj.presentation.pref.SharedPrefsKey

abstract class BaseActivity<B : ViewBinding, T : ViewModel>(val bindingFactory: (LayoutInflater) -> B) :
    AppCompatActivity() {
    protected val binding: B by lazy { bindingFactory(layoutInflater) }

    var localeDelegate = LocaleHelperActivityDelegateImpl()

    open fun initDarkMode() {
        setTheme(R.style.Theme_WeatherJ)
        val themeMode = AppPrefs.getInt(SharedPrefsKey.KEY_DARKMODE)
        ThemeHelper.applyTheme(themeMode)
    }

    fun isDarkTheme(): Boolean {
        val uiMode = this.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return uiMode == Configuration.UI_MODE_NIGHT_YES
    }

    override fun attachBaseContext(newBase: Context?) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        try {
            var context = localeDelegate.attachBaseContext(newBase)
            if (context == null) {
                context = newBase
            }
            super.attachBaseContext(context)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.initDarkMode()
        initAnim()
        setContentView(binding.root)
        observeData()
        setUpViews()
    }

    open fun setUpViews() {
    }

    open suspend fun setupDatas() {

    }

    open fun observeData() {
        //do nothing
    }

    private fun initAnim() {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        val exit = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {

            // Only run the transition on the contents of this activity, excluding
            // system bars or app bars if provided by the app’s theme.
            addTarget(binding.root)
        }
        window.exitTransition = exit.addTarget(binding.root)
        window.allowEnterTransitionOverlap = true
    }

    override fun onLowMemory() {
        super.onLowMemory()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
    }

    protected fun startActivity(clazz: Class<*>?) {
        val intent = Intent(this, clazz).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    protected fun startActivity(clazz: Class<*>?, bundle: Bundle?) {
        val intent = Intent(this, clazz).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtras(bundle!!)
        startActivity(intent)
    }
}
