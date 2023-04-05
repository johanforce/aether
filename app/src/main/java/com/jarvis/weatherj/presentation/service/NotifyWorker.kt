package com.jarvis.weatherj.presentation.service

import android.annotation.SuppressLint
import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jarvis.domain.usecase.WeatherUseCase
import com.jarvis.weatherj.MainApplication
import com.jarvis.weatherj.R
import com.jarvis.weatherj.presentation.common.DataUtils
import com.jarvis.weatherj.presentation.common.isConnected
import com.jarvis.weatherj.presentation.pref.AppPrefs
import com.jarvis.weatherj.presentation.pref.SharedPrefsKey
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltWorker
class NotifyWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters
) :
    CoroutineWorker(appContext, workerParams) {

    @Inject
    lateinit var weatherUseCase: WeatherUseCase

    @SuppressLint("CheckResult")
    override suspend fun doWork(): Result {
        if (!MainApplication.applicationContext().isConnected()) return Result.success()
        val context = MainApplication.applicationContext().applicationContext
        CoroutineScope(Dispatchers.IO).launch {
            val city = AppPrefs.getString(SharedPrefsKey.KEY_PREF_LOCATION)
            val data = withContext(Dispatchers.IO) {
                weatherUseCase(city)
            }
            val statusWeather = DataUtils.convertTitleWeather(
                data.currentCondition?.get(0)?.weatherCode?.toInt() ?: 0
            )
            val tempCurrent =
                context.getString(R.string.current_temp, data.currentCondition?.get(0)?.tempC)
            val imageWeather = DataUtils.convertImageWeather(
                data.currentCondition?.get(0)?.weatherCode?.toInt() ?: 0
            )
            val des = tempCurrent + ", " + context.getString(statusWeather)
            NotificationUtils.createNotification(
                context,
                context.getString(R.string.noti_title),
                des,
                imageWeather
            )
        }
        return Result.success()
    }
}
