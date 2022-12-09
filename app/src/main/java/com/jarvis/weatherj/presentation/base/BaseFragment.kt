package com.jarvis.weatherj.presentation.base

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.jarvis.design_system.toolbar.JxToolbar
import com.jarvis.weatherj.MainApplication
import com.jarvis.weatherj.presentation.common.isConnected
import com.jarvis.weatherj.presentation.common.pref.AppPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@Suppress("unused")
abstract class BaseFragment<Binding : ViewBinding>(val bindingFactory: (LayoutInflater) -> Binding) :
    Fragment(), CoroutineScope {
    var appPreference: AppPreference? = null
    protected open fun getToolbar(): JxToolbar? {
        return null
    }

    fun initToolbar() {
        val toolbar: JxToolbar = getToolbar() ?: return
        val parentActivity = activity as AppCompatActivity?
        if (parentActivity != null) {
            setHasOptionsMenu(true)
            parentActivity.setSupportActionBar(toolbar.toolbar)
        }
    }

    protected fun isNetworkAvailable(): Boolean {
        return MainApplication.applicationContext().isConnected()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val toolbar: JxToolbar? = getToolbar()
        if (toolbar != null && this.activity != null) {
            toolbar.onCreateOptionsMenu(this.activity as AppCompatActivity?, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val toolbar: JxToolbar? = getToolbar()
        return toolbar?.onOptionsItemSelected(item) ?: super.onOptionsItemSelected(item!!)
    }

    protected val binding: Binding by lazy { bindingFactory(layoutInflater) }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job
    open var useSharedViewModel: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job() // create the Job
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
        appPreference = AppPreference.getInstance()
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
