package com.pa.dailychallengecards.presentation.challenges

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.model.ChallengeStatus
import com.pa.dailychallengecards.domain.use_case.ChallengeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChallengesViewModel @Inject constructor(
    private val challengeUseCases: ChallengeUseCases
) : ViewModel() {

    var showCompletedChallenges by mutableStateOf(false)
        private set

    fun showCompletedChallenges() {
        showCompletedChallenges = true
    }

    fun hideCompletedChallenges() {
        showCompletedChallenges = false
    }

    private suspend fun getDailySelection() : List<Challenge> {
        var dailySelection = listOf<Challenge>()
        challengeUseCases.getDailySelection(activeStatus = ChallengeStatus.Active)
            .collect { dailyChallenges ->
                dailySelection = dailyChallenges
            }
        return dailySelection
    }

    private suspend fun rollDailySelection() {
        challengeUseCases.rollDailySelection(
            initialStatus = ChallengeStatus.Idle,
            desiredStatus = ChallengeStatus.Active,
            numberOfChallenges = numberOfDailyChallenges
        )
    }

    private suspend fun resetChallengesStatus() {
        challengeUseCases.resetChallengesStatus(
            active = ChallengeStatus.Active,
            completed = ChallengeStatus.Completed,
            idle = ChallengeStatus.Idle
        )
    }

    private suspend fun updateChallengeStatus(id : Int, desiredStatus : ChallengeStatus){
        challengeUseCases.updateChallengeStatus(id = id, desiredStatus = desiredStatus)
    }


    companion object{
        const val numberOfDailyChallenges = 3
    }
}
