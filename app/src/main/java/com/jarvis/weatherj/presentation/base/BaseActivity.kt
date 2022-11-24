@file:Suppress("MemberVisibilityCanBePrivate")

package com.jarvis.weatherj.presentation.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.jarvis.weatherj.presentation.common.pref.AppPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<B : ViewBinding, T : ViewModel>(val bindingFactory: (LayoutInflater) -> B) :
    AppCompatActivity(), CoroutineScope {
    protected val binding: B by lazy { bindingFactory(layoutInflater) }
    var appPreference: AppPreference? = null
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appPreference = AppPreference.getInstance()
        job = Job()
        initAnim()
        setContentView(binding.root)
        observeData()
        initCoroutineScope()
        setUpViews()
    }

    open fun setUpViews() {
    }

    open fun initCoroutineScope() {
        launch {
            setupDatas()
        }
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
//        overridePendingTransition(R.anim.anim_right_in, R.anim.anim_right_out)
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

    override fun onDestroy() {
        job.cancel() // cancel the Job
        super.onDestroy()
    }
}
