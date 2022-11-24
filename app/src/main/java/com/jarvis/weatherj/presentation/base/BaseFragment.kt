package com.jarvis.weatherj.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.jarvis.weatherj.presentation.common.pref.AppPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@Suppress("unused")
abstract class BaseFragment<Binding : ViewBinding>(val bindingFactory: (LayoutInflater) -> Binding) :
    Fragment(), CoroutineScope {

    protected val binding: Binding by lazy { bindingFactory(layoutInflater) }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job
    var appPreference: AppPreference? = null
    open var useSharedViewModel: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job() // create the Job
        appPreference = AppPreference.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedInstanceState?.let { this.onSaveInstanceState(it) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeData()
        initCoroutineScope()
    }

    open fun initCoroutineScope() {
        launch {
            setupDatas()
        }
    }

    open suspend fun setupDatas() {

    }

    open fun setUpViews() {}

    open fun observeData() {}

    override fun onDestroy() {
        job.cancel() // cancel the Job
        super.onDestroy()
    }
}
