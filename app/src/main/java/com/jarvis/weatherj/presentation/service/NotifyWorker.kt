///*
// * Copyright © OMRON HEALTHCARE Co., Ltd. 2020. All rights reserved.
// */
//
//package com.jarvis.weatherj.presentation.service
//
//import android.annotation.SuppressLint
//import android.content.Context
//import androidx.work.Worker
//import androidx.work.WorkerParameters
//import com.jarvis.weatherj.MainApplication
//import com.jarvis.weatherj.R
//import com.jarvis.weatherj.domain.usecase.WeatherUseCase
//import com.jarvis.weatherj.presentation.common.DataUtils
//import com.jarvis.weatherj.presentation.pref.AppPrefs
//import com.jarvis.weatherj.presentation.pref.SharedPrefsKey
//import kotlinx.coroutines.*
//import javax.inject.Inject
//
//
//class NotifyWorker(appContext: Context, workerParams: WorkerParameters) :
//    Worker(appContext, workerParams) {
//
//    @Inject
//    lateinit var weatherUseCase: WeatherUseCase
//
//    @SuppressLint("CheckResult")
//    override fun doWork(): Result {
////        MainApplication.applicationContext().appComponent().inject(this)
//        val context = MainApplication.applicationContext().applicationContext
//        CoroutineScope(Dispatchers.IO).launch {
//            val city = AppPrefs.getString(SharedPrefsKey.KEY_PREF_LOCATION)
//            val data = withContext(Dispatchers.IO) {
//                weatherUseCase(city)
//            }
//            val statusWeather = DataUtils.convertTitleWeather(
//                data.current_condition?.get(0)?.weatherCode?.toInt() ?: 0
//            )
//            val tempCurrent =
//                context.getString(R.string.current_temp, data.current_condition?.get(0)?.temp_C)
//            val imageWeather = DataUtils.convertImageWeather(
//                data.current_condition?.get(0)?.weatherCode?.toInt() ?: 0
//            )
//            val des = tempCurrent + ", " + context.getString(statusWeather)
//            NotificationUtils.createNotification(
//                context,
//                context.getString(R.string.noti_title),
//                des,
//                imageWeather
//            )
//        }
//
//
//        // Your work here.
//        // Your task result
//        return Result.success()
//    }
//}
