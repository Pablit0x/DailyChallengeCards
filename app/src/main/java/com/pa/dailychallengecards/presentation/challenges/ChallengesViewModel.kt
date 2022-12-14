package com.pa.dailychallengecards.presentation.challenges

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.model.ChallengeStatus
import com.pa.dailychallengecards.domain.use_case.ChallengeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ChallengesViewModel @Inject constructor(
    private val challengeUseCases: ChallengeUseCases
) : ViewModel() {


    var timeEnd = LocalDateTime.now()

    fun startClock(){
        timeEnd = LocalDateTime.now().plusHours(6);
    }

    fun updateTime() : Duration{
        return Duration.between(LocalDateTime.now(),timeEnd)
    }

    var showCompletedChallenges by mutableStateOf(false)
        private set

    fun showCompletedChallenges() {
        showCompletedChallenges = true
    }

    fun hideCompletedChallenges() {
        showCompletedChallenges = false
    }

    val dailySelection = challengeUseCases.getDailySelection(activeStatus = ChallengeStatus.Active)

    val getSelectedChallenge = challengeUseCases.getCurrentSelected(activeStatus = ChallengeStatus.Active)

    fun updateChallengeStatus(id: Int, desiredStatus: ChallengeStatus) =
        viewModelScope.launch(Dispatchers.IO) {
            challengeUseCases.updateChallengeStatus(id = id, desiredStatus = desiredStatus)
        }

    fun addChallenge(challenge: Challenge) = viewModelScope.launch(Dispatchers.IO) {
        challengeUseCases.addChallenge(challenge = challenge)
    }

    fun deleteAllChallenges() = viewModelScope.launch(Dispatchers.IO){
        challengeUseCases.deleteAllChallenges()
    }

    companion object {
        const val numberOfDailyChallenges = 3
    }

}
