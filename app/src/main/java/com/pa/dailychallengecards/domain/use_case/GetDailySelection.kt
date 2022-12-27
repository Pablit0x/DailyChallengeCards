package com.pa.dailychallengecards.domain.use_case

import com.pa.dailychallengecards.domain.model.ChallengeStatus
import com.pa.dailychallengecards.domain.repository.ChallengeRepository

class GetDailySelection(
    private val challengeRepository: ChallengeRepository
) {
    operator fun invoke(activeStatus: ChallengeStatus) =
        challengeRepository.getDailySelection(activeStatus = activeStatus)
}