package com.pa.dailychallengecards.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.pa.dailychallengecards.domain.model.ChallengeStatus
import com.pa.dailychallengecards.domain.notification.DailyNotificationService
import com.pa.dailychallengecards.domain.use_case.ChallengeUseCases
import com.pa.dailychallengecards.presentation.challenges.ChallengesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(DelicateCoroutinesApi::class)
@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var dailyNotificationService: DailyNotificationService

    @Inject
    lateinit var challengesUseCases: ChallengeUseCases

    override fun onReceive(context: Context?, intent: Intent?) {
        dailyNotificationService.displayDailyNotification()
        rollDailySelection()
    }

    private fun rollDailySelection() {
        GlobalScope.launch {
            challengesUseCases.resetDailySelection.invoke(
                active = ChallengeStatus.Active,
                selected = ChallengeStatus.Selected,
                idle = ChallengeStatus.Idle
            )
            delay(1000)
            challengesUseCases.rollDailySelection.invoke(
                initialStatus = ChallengeStatus.Idle,
                desiredStatus = ChallengeStatus.Active,
                numberOfChallenges = ChallengesViewModel.numberOfDailyChallenges
            )
        }
    }
}