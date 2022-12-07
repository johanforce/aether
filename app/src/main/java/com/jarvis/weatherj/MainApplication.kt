package com.jarvis.weatherj

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.multidex.MultiDexApplication
import com.google.gson.Gson
import dagger.hilt.android.HiltAndroidApp

/**
 * Use Hilt must contain an Application class that is annotated with @HiltAndroidApp
 * @HiltAndroidApp triggers Hilt's code generation
 */
@HiltAndroidApp
class MainApplication : MultiDexApplication(), Application.ActivityLifecycleCallbacks{
    lateinit var gson: Gson

    companion object {
        private var instance: MainApplication? = null
        var isCountDownTime = false

        fun applicationContext(): MainApplication {
            return instance as MainApplication
        }
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        gson = Gson()
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onActivityStarted(p0: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivityResumed(p0: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivityPaused(p0: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivityStopped(p0: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
        TODO("Not yet implemented")
    }

    override fun onActivityDestroyed(p0: Activity) {
        isCountDownTime = false
    }

}
