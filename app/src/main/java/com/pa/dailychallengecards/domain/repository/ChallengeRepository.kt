package com.pa.dailychallengecards.domain.repository

import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.model.ChallengeStatus
import kotlinx.coroutines.flow.Flow


interface ChallengeRepository {

    fun getDailySelection(): Flow<List<Challenge>>

    suspend fun updateChallengeStatus(id : Int, challengeStatus: ChallengeStatus)

    fun getChallengesByChallengeStatus(challengeStatus: ChallengeStatus) : Flow<List<Challenge>>



    // Admin Queries
    fun getAllChallenges(): Flow<List<Challenge>>

    suspend fun addChallenge(challenge: Challenge)

    suspend fun deleteChallenge(challengeId: Int)
}
