/*
 * Copyright © OMRON HEALTHCARE Co., Ltd. 2020. All rights reserved.
 */

package com.jarvis.weatherj.presentation.service

import androidx.work.Constraints
import androidx.work.Data
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.jarvis.weatherj.MainApplication
import java.util.*
import java.util.concurrent.TimeUnit

object AlarmUtils {

    const val ALARM_WEATHER_8 = "ALARM_WEATHER_8"
    const val ALARM_WEATHER_16 = "ALARM_WEATHER_16"

    fun schedulePeriodicWork(tag: String, time: Long) {
        val workConstraints = Constraints.Builder().build()
        // using Data.Builder
        val inputData = Data.Builder()
            .build()

        val calendar = Calendar.getInstance()
        val nowMillis = calendar.timeInMillis
        calendar.timeInMillis = time


        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1);
        }
        val diff: Long = calendar.timeInMillis - nowMillis

        // declare repeating work at specific time of day
        val periodicWork = PeriodicWorkRequestBuilder<NotifyWorker>(1, TimeUnit.DAYS)
            .addTag(tag)
            .setConstraints(workConstraints)
            .setInputData(inputData)
            .setInitialDelay(diff, TimeUnit.MILLISECONDS)
            .build()

        // register repeated work on time
        WorkManager.getInstance(MainApplication.applicationContext()).enqueue(periodicWork)
    }

    fun cancelWork(workTag: String) {
        WorkManager.getInstance(MainApplication.applicationContext()).cancelAllWorkByTag(workTag)
    }
}
