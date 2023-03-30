package com.jarvis.weatherj

import androidx.hilt.work.HiltWorkerFactory
import androidx.lifecycle.LifecycleObserver
import androidx.multidex.MultiDexApplication
import androidx.work.Configuration
import com.google.gson.Gson
import com.jarvis.weatherj.presentation.common.FireBaseLogEvents
import com.jarvis.weatherj.presentation.service.AlarmUtils
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp
import java.util.*
import javax.inject.Inject

/**
 * Use Hilt must contain an Application class that is annotated with @HiltAndroidApp
 * @HiltAndroidApp triggers Hilt's code generation
 */
@HiltAndroidApp
class MainApplication : MultiDexApplication(), LifecycleObserver, Configuration.Provider {
    lateinit var gson: Gson

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    companion object {
        private var instance: MainApplication? = null
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

        createNotiWeather()
    }

    private fun createNotiWeather() {
        AlarmUtils.cancelWork(AlarmUtils.ALARM_WEATHER_8)
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 8)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        AlarmUtils.schedulePeriodicWork(AlarmUtils.ALARM_WEATHER_8, cal.timeInMillis)
        AlarmUtils.cancelWork(AlarmUtils.ALARM_WEATHER_16)
        cal.set(Calendar.HOUR_OF_DAY, 16)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        AlarmUtils.schedulePeriodicWork(AlarmUtils.ALARM_WEATHER_16, cal.timeInMillis)
    }

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .build()

}
