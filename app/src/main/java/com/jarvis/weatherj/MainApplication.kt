package com.jarvis.weatherj

import androidx.lifecycle.LifecycleObserver
import androidx.multidex.MultiDexApplication
import com.google.gson.Gson
import com.jarvis.weatherj.di.AppComponent
import com.jarvis.weatherj.presentation.common.FireBaseLogEvents
//import com.jarvis.weatherj.presentation.service.AlarmUtils
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp
import java.util.*

/**
 * Use Hilt must contain an Application class that is annotated with @HiltAndroidApp
 * @HiltAndroidApp triggers Hilt's code generation
 */
@HiltAndroidApp
class MainApplication : MultiDexApplication(), LifecycleObserver {
    lateinit var gson: Gson

//    private lateinit var appComponent: AppComponent

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

        FireBaseLogEvents.init(this)
        gson = Gson()
        MMKV.initialize(this)

//        createNotiWeather()
    }

//    private fun createNotiWeather() {
//        val cal = Calendar.getInstance()
//        cal.set(Calendar.HOUR, 8)
//        cal.set(Calendar.MINUTE, 0)
//        cal.set(Calendar.SECOND, 0)
//        cal.set(Calendar.MILLISECOND, 0)
//        AlarmUtils.schedulePeriodicWork(AlarmUtils.ALARM_WEATHER_8, cal.timeInMillis)
//
//        cal.set(Calendar.HOUR, 16)
//        cal.set(Calendar.MINUTE, 0)
//        cal.set(Calendar.SECOND, 0)
//        cal.set(Calendar.MILLISECOND, 0)
//        AlarmUtils.schedulePeriodicWork(AlarmUtils.ALARM_WEATHER_16, cal.timeInMillis)
//    }

//    open fun appComponent(): AppComponent {
//        return appComponent
//    }
}
