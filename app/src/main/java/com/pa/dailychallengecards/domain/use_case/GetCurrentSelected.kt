package com.pa.dailychallengecards.domain.use_case

import com.pa.dailychallengecards.domain.model.ChallengeStatus
import com.pa.dailychallengecards.domain.repository.ChallengeRepository

class GetCurrentSelected(
    private val challengeRepository: ChallengeRepository
) {
    operator fun invoke(activeStatus: ChallengeStatus) =
        challengeRepository.getCurrentlySelected()
}