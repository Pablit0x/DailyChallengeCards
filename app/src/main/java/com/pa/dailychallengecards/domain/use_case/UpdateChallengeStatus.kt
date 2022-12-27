package com.pa.dailychallengecards.domain.use_case

import com.pa.dailychallengecards.domain.model.ChallengeStatus
import com.pa.dailychallengecards.domain.repository.ChallengeRepository

class UpdateChallengeStatus(
    private val challengeRepository: ChallengeRepository
) {
    operator fun invoke(id: Int, desiredStatus: ChallengeStatus) {
        challengeRepository.updateChallengeStatus(id = id, desiredStatus = desiredStatus)
    }
}