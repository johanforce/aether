package com.jarvis.weatherj.presentation.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.jarvis.weatherj.MainApplication
import com.jarvis.weatherj.R
import com.jarvis.weatherj.presentation.main.MainActivity

object NotificationUtils {

    /**
     * This is the method to create notification channel
     */
    private fun createNotificationChannel(
        context: Context,
        channelId: String,
        notificationManager: NotificationManager
    ) {
        if (notificationManager.notificationChannels.any { it.id == channelId }) {
            return
        }
        val name = context.getString(R.string.notification_channel_name)
        val description = context.getString(R.string.notification_channel_description)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelId, name, importance)

        channel.apply {
            if (channelId == MainApplication.applicationContext()
                    .getString(R.string.default_notification_channel)
            ) {
                this.description = description
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
                vibrationPattern = longArrayOf(100, 200, 300, 400, 500)
            } else {
                setSound(null, null)
                enableLights(false)
                enableVibration(false)
            }
        }
        notificationManager.createNotificationChannel(channel)
    }

    /**
     * This is the method to create notification for remind users to take medication and measure blood pressure
     */
    fun createNotification(
        context: Context,
        title: String?,
        body: String?,
        image: Int
    ): Notification {
        val channelId =
            MainApplication.applicationContext().getString(R.string.default_notification_channel)
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(context, MainActivity::class.java)

        val contentIntent =
            PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(image)
            .setContentText(body)
            .setContentTitle(title)
            .setAutoCancel(true)
            .setContentIntent(contentIntent)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setColor(ContextCompat.getColor(context, android.R.color.holo_blue_bright))
            .setDefaults(Notification.DEFAULT_VIBRATE)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        createNotificationChannel(context, channelId, notificationManager)
        val notification = notificationBuilder.build()
        notificationManager.notify(System.currentTimeMillis().toInt(), notification)
        return notification
    }
}
