package com.pa.dailychallengecards.domain.use_case

import com.pa.dailychallengecards.domain.model.ChallengeStatus
import com.pa.dailychallengecards.domain.repository.ChallengeRepository

class RollDailySelection(
    private val challengeRepository: ChallengeRepository
) {
    operator fun invoke(
        initialStatus: ChallengeStatus,
        desiredStatus: ChallengeStatus,
        numberOfChallenges: Int
    ) =
        challengeRepository.rollDailySelection(
            initialStatus = initialStatus,
            desiredStatus = desiredStatus,
            numberOfChallenges = numberOfChallenges
        )
}