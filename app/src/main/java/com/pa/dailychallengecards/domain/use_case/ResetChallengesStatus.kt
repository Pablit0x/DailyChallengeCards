package com.pa.dailychallengecards.domain.use_case

import com.pa.dailychallengecards.domain.model.ChallengeStatus
import com.pa.dailychallengecards.domain.repository.ChallengeRepository

class ResetChallengesStatus(
    private val challengeRepository: ChallengeRepository
) {
    suspend operator fun invoke(
        active: ChallengeStatus,
        completed: ChallengeStatus,
        idle: ChallengeStatus
    ) =
        challengeRepository.resetChallengesStatus(
            active = active,
            completed = completed,
            idle = idle
        )
}