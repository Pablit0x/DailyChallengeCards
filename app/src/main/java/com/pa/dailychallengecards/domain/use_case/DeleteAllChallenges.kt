package com.pa.dailychallengecards.domain.use_case

import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.repository.ChallengeRepository

class DeleteAllChallenges(
    private val challengeRepository: ChallengeRepository
) {
    operator fun invoke() = challengeRepository.deleteAllChallenges()
}