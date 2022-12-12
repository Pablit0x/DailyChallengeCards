package com.pa.dailychallengecards.presentation.challenges

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

    val dailySelection = challengeUseCases.getDailySelection(activeStatus = ChallengeStatus.Active)

    fun updateChallengeStatus(id: Int, desiredStatus: ChallengeStatus) =
        viewModelScope.launch(Dispatchers.IO) {
            challengeUseCases.updateChallengeStatus(id = id, desiredStatus = desiredStatus)
        }

    fun addChallenge(challenge: Challenge) = viewModelScope.launch(Dispatchers.IO) {
        challengeUseCases.addChallenge(challenge = challenge)
    }


    companion object {
        const val numberOfDailyChallenges = 3
    }
}
