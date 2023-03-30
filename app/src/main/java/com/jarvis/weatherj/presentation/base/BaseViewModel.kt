package com.jarvis.weatherj.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jarvis.weatherj.common.DebugLog
import com.jarvis.weatherj.common.error.handleException
import com.jarvis.weatherj.common.utils.SingleLiveData
import kotlinx.coroutines.*

open class BaseViewModel : ViewModel() {

    val mLoading = SingleLiveData<Boolean>()
    val mError = SingleLiveData<Throwable>()

    private val debugLog: DebugLog by lazy { DebugLog() }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        debugLog.e(throwable.stackTraceToString())
        mError.value = handleException(throwable)
        mLoading.value = false
    }

    protected val scope = viewModelScope.plus(exceptionHandler)

    /**
     * @param scope coroutine scope to execute task
     * @param onError the callback run when had error
     * @param onExecute the action to execute task
     * */
    protected fun <T> executeTask(
        scope: CoroutineScope = this.scope,
        onError: ((Exception) -> Unit)? = null,
        onExecute: suspend () -> T,
    ) {
        scope.launch(Dispatchers.Main) {
            try {
                onExecute()
            } catch (e: Exception) {
                onError?.invoke(e) ?: throw e
            }
        }
    }
}