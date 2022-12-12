package com.pa.dailychallengecards.data.repository

import com.pa.dailychallengecards.data.data_source.ChallengeDao
import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.model.ChallengeStatus
import com.pa.dailychallengecards.domain.repository.ChallengeRepository

class ChallengeRepositoryImpl(
    private val challengeDao: ChallengeDao
) : ChallengeRepository {

    override fun getDailySelection(activeStatus: ChallengeStatus) =
        challengeDao.getDailySelection(activeStatus = activeStatus)

    override fun rollDailySelection(
        initialStatus: ChallengeStatus,
        desiredStatus: ChallengeStatus,
        numberOfChallenges: Int
    ) = challengeDao.rollDailySelection(
        initialStatus = initialStatus,
        desiredStatus = desiredStatus,
        numberOfChallenges = numberOfChallenges
    )

    override fun resetDailySelection(
        active: ChallengeStatus,
        selected: ChallengeStatus,
        idle: ChallengeStatus
    ) = challengeDao.resetDailySelection(active = active, selected = selected, idle = idle)

    override fun updateChallengeStatus(
        id: Int,
        desiredStatus: ChallengeStatus
    ) = challengeDao.updateChallengeStatus(id = id, desiredStatus = desiredStatus)

    override fun getCompletedChallenges(completedStatus: ChallengeStatus) =
        challengeDao.getCompletedChallenges(completedStatus = completedStatus)

    override fun addChallenge(challenge: Challenge) = challengeDao.addChallenge(challenge)
}