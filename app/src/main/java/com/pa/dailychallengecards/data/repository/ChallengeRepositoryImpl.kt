package com.pa.dailychallengecards.data.repository

import com.pa.dailychallengecards.data.data_source.ChallengeDao
import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.model.ChallengeStatus
import com.pa.dailychallengecards.domain.repository.ChallengeRepository
import kotlinx.coroutines.flow.Flow

class ChallengeRepositoryImpl(
    private val challengeDao: ChallengeDao
) : ChallengeRepository {

    override fun getDailySelection(activeStatus: ChallengeStatus): Flow<List<Challenge>> {
        return challengeDao.getDailySelection(activeStatus = activeStatus)
    }

    override suspend fun rollDailySelection(
        initialStatus: ChallengeStatus,
        desiredStatus: ChallengeStatus,
        numberOfChallenges: Int
    ) {
        challengeDao.rollDailySelection(
            initialStatus = initialStatus,
            desiredStatus = desiredStatus,
            numberOfChallenges = numberOfChallenges
        )
    }

    override suspend fun resetChallengesStatus(
        active: ChallengeStatus,
        completed: ChallengeStatus,
        idle: ChallengeStatus
    ) {
        challengeDao.resetChallengesStatus(active = active, completed = completed, idle = idle)
    }

    override suspend fun updateChallengeStatus(
        id: Int,
        desiredStatus: ChallengeStatus
    ) {
        challengeDao.updateChallengeStatus(id = id, desiredStatus = desiredStatus)
    }

    override fun getCompletedChallenges(completedStatus: ChallengeStatus): Flow<List<Challenge>> {
        return challengeDao.getCompletedChallenges(completedStatus = completedStatus)
    }

    override suspend fun addChallenge(challenge: Challenge) {
        challengeDao.addChallenge(challenge)
    }
}