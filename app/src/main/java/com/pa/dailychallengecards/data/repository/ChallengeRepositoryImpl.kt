package com.pa.dailychallengecards.data.repository

import com.pa.dailychallengecards.data.data_source.ChallengeDao
import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.model.ChallengeStatus
import com.pa.dailychallengecards.domain.repository.ChallengeRepository
import kotlinx.coroutines.flow.Flow

class ChallengeRepositoryImpl(
    private val challengeDao: ChallengeDao
) : ChallengeRepository {

    override fun getDailySelection(): Flow<List<Challenge>> {
        return challengeDao.getDailySelection()
    }

    override suspend fun updateChallengeStatus(
        id: Int,
        challengeStatus: ChallengeStatus
    ) {
        challengeDao.updateChallengeStatus(id = id, challengeStatus = challengeStatus)
    }

    override fun getChallengesByChallengeStatus(challengeStatus: ChallengeStatus): Flow<List<Challenge>> {
        return challengeDao.getChallengesByChallengeStatus(challengeStatus = challengeStatus)
    }

    override fun getAllChallenges(): Flow<List<Challenge>> {
        return challengeDao.getAllChallenges()
    }

    override suspend fun addChallenge(challenge: Challenge) {
        challengeDao.addChallenge(challenge = challenge)
    }

    override suspend fun deleteChallenge(challengeId: Int) {
        challengeDao.deleteChallenge(challengeId = challengeId)
    }
}