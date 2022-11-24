@file:Suppress("unused")

package com.jarvis.weatherj.presentation.base.data

import androidx.lifecycle.MutableLiveData

class StateLiveData<T> : MutableLiveData<StateData<T>?>() {
    /**
     * Use this to put the Data on a LOADING Status
     */
    fun postLoading() {
        postValue(StateData<T>().loading())
    }

    /**
     * Use this to put the Data on a ERROR DataStatus
     * @param throwable the error to be handled
     */
    fun postError(throwable: Throwable?) {
        postValue(StateData<T>().error(throwable!!))
    }

    /**
     * Use this to put the Data on a SUCCESS DataStatus
     * @param data
     */
    fun postSuccess(data: T) {
        postValue(StateData<T>().success(data))
    }

    /**
     * Use this to put the Data on a COMPLETE DataStatus
     */
    fun postComplete() {
        postValue(StateData<T>().complete())
    }
}
