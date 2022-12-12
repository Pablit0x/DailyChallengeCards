package com.pa.dailychallengecards.domain.repository

import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.model.ChallengeStatus
import kotlinx.coroutines.flow.Flow


interface ChallengeRepository {

    fun getDailySelection(activeStatus: ChallengeStatus): Flow<List<Challenge>>

    suspend fun rollDailySelection(
        initialStatus: ChallengeStatus,
        desiredStatus: ChallengeStatus,
        numberOfChallenges: Int
    )

    suspend fun resetChallengesStatus(
        active: ChallengeStatus,
        completed: ChallengeStatus,
        idle: ChallengeStatus
    )

    suspend fun updateChallengeStatus(id: Int, desiredStatus: ChallengeStatus)

    fun getCompletedChallenges(completedStatus: ChallengeStatus): Flow<List<Challenge>>

    suspend fun addChallenge(challenge: Challenge)
}
