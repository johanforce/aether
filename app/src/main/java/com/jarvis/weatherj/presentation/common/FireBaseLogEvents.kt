@file:Suppress("unused", "UNUSED_PARAMETER", "DEPRECATION")

package com.jarvis.weatherj.presentation.common

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.annotation.Size
import com.google.firebase.analytics.FirebaseAnalytics

class FireBaseLogEvents private constructor(context: Context) {
    private val mFirebaseAnalytics: FirebaseAnalytics

    init {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context)
    }

    fun log(@Size(min = 1L, max = 32L) eventName: String, eventBundle: Bundle?) {
        if (eventBundle != null) {
            mFirebaseAnalytics.logEvent(eventName, eventBundle)
            return
        }
        mFirebaseAnalytics.logEvent(eventName, null)
    }

    fun log(@Size(min = 1L, max = 32L) eventName: String, paramKey: String, paramValue: String) {
        val bundle = Bundle()
        bundle.putString(paramKey, paramValue)
        mFirebaseAnalytics.logEvent(eventName, bundle)
    }

    fun log(@Size(min = 1L, max = 32L) eventName: String) {
        mFirebaseAnalytics.logEvent(eventName, null)
    }

    fun log(@Size(min = 1L, max = 32L) eventName: String, message: String) {
        mFirebaseAnalytics.logEvent(eventName, null)
    }

    fun setCurrentScreen(activity: Activity?, screenName: String?) {
        mFirebaseAnalytics.setCurrentScreen(activity!!, screenName, null)
    }

    companion object {
        private var instance: FireBaseLogEvents? = null
        fun getInstance(): FireBaseLogEvents? {
            if (instance == null) {
                throw NullPointerException("Please init AppLogEvent on App Application!")
            }
            return instance
        }

        fun init(context: Context) {
            if (instance == null) {
                instance = FireBaseLogEvents(context)
            }
        }
    }
}
