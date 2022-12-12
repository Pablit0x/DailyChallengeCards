package com.pa.dailychallengecards.domain.repository

import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.model.ChallengeStatus
import kotlinx.coroutines.flow.Flow


interface ChallengeRepository {

    fun getDailySelection(activeStatus: ChallengeStatus): Flow<List<Challenge>>

    fun rollDailySelection(
        initialStatus: ChallengeStatus,
        desiredStatus: ChallengeStatus,
        numberOfChallenges: Int
    )

    fun resetDailySelection(
        active: ChallengeStatus,
        selected: ChallengeStatus,
        idle: ChallengeStatus
    )

    fun updateChallengeStatus(id: Int, desiredStatus: ChallengeStatus)

    fun getCompletedChallenges(completedStatus: ChallengeStatus): Flow<List<Challenge>>

    fun addChallenge(challenge: Challenge)
}
