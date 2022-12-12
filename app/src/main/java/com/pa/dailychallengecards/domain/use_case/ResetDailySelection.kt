package com.pa.dailychallengecards.domain.use_case

import com.pa.dailychallengecards.domain.model.ChallengeStatus
import com.pa.dailychallengecards.domain.repository.ChallengeRepository

class ResetDailySelection(
    private val challengeRepository: ChallengeRepository
) {
    operator fun invoke(
        active: ChallengeStatus,
        selected: ChallengeStatus,
        idle: ChallengeStatus
    ) =
        challengeRepository.resetDailySelection(
            active = active,
            selected = selected,
            idle = idle
        )
}