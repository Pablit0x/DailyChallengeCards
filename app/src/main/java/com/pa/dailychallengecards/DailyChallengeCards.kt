package com.pa.dailychallengecards

import android.app.Application
import android.util.Log
import com.pa.dailychallengecards.domain.notification.DailyNotificationService
import com.pa.dailychallengecards.util.AlarmManager
import com.pa.dailychallengecards.util.Constants
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DailyChallengeCards : Application() {

    @Inject
    lateinit var dailyNotificationService: DailyNotificationService

    @Inject
    lateinit var alarmManager: AlarmManager

    override fun onCreate() {
        super.onCreate()
        dailyNotificationService.createNotificationChannel()
        alarmManager.enableAlarm()
    }
}