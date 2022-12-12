package com.pa.dailychallengecards.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.pa.dailychallengecards.domain.notification.DailyNotificationService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var dailyNotificationService: DailyNotificationService

    override fun onReceive(context: Context?, intent: Intent?) {
        dailyNotificationService.displayDailyNotification()
    }
}