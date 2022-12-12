package com.pa.dailychallengecards.domain.notification


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.pa.dailychallengecards.MainActivity
import com.pa.dailychallengecards.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DailyNotificationService @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val notificationManager = context
        .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun displayDailyNotification() {
        val openMainActivityIntent = Intent(context, MainActivity::class.java)
        val openMainActivityPendingIntent = PendingIntent.getActivity(
            context,
            OPEN_ACTIVITY_REQUEST,
            openMainActivityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(context, DAILY_NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(DAILY_NOTIFICATION_TITLE)
            .setContentText(DAILY_NOTIFICATION_CONTENT)
            .setContentIntent(openMainActivityPendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(DAILY_NOTIFICATION_ID, notification)
    }

    fun createNotificationChannel() {
        val dailyNotificationChannel = NotificationChannel(
            DAILY_NOTIFICATION_CHANNEL_ID,
            DAILY_NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        dailyNotificationChannel.description = DAILY_NOTIFICATION_CHANNEL_DESCRIPTION
        val notificationManager = context
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(dailyNotificationChannel)
    }

    companion object {

        const val DAILY_NOTIFICATION_CHANNEL_ID = "DAILY_NOTIFICATION_CHANNEL_ID"
        const val DAILY_NOTIFICATION_TITLE = "Notification Title..."
        const val DAILY_NOTIFICATION_CONTENT =
            "Notification Content..."

        const val DAILY_NOTIFICATION_CHANNEL_NAME =
            "Daily Notifications"
        const val DAILY_NOTIFICATION_CHANNEL_DESCRIPTION =
            "Daily notification used to inform the user about the new active challenges"

        const val OPEN_ACTIVITY_REQUEST = 1
        const val DAILY_NOTIFICATION_ID = 1
        const val ALARM_REQUEST = 1
    }
}
