package com.jarvis.weatherj.presentation.base

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.jarvis.weatherj.MainApplication
import com.jarvis.weatherj.presentation.common.isConnected

@Suppress("unused")
abstract class BaseFragment<Binding : ViewBinding>(val bindingFactory: (LayoutInflater) -> Binding) :
    Fragment() {

    protected fun isNetworkAvailable(): Boolean {
        return MainApplication.applicationContext().isConnected()
    }

    protected val binding: Binding by lazy { bindingFactory(layoutInflater) }

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
    }

    open fun setUpViews() {}

    open fun observeData() {}
}
