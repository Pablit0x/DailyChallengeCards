package com.pa.dailychallengecards.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.pa.dailychallengecards.domain.notification.DailyNotificationService
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

class AlarmManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private var alarmTime: Calendar

    init {
        alarmTime = setAlarmTime()
    }

    private fun setAlarmTime(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR, alarmHour)
        calendar.set(Calendar.MINUTE, alarmMinute)
        calendar.set(Calendar.SECOND, alarmSecond)
        return calendar
    }

    fun enableAlarm() {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(context, AlarmReceiver::class.java)
        val alarmPendingIntent = PendingIntent.getBroadcast(
            context,
            DailyNotificationService.ALARM_REQUEST,
            alarmIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            alarmTime.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            alarmPendingIntent
        )
    }

    companion object {
        const val alarmHour = 8
        const val alarmMinute = 0
        const val alarmSecond = 0
    }
}