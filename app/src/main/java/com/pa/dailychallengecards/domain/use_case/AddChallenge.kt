package com.pa.dailychallengecards.domain.use_case

import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.repository.ChallengeRepository

class AddChallenge(
    private val challengeRepository: ChallengeRepository
) {
    suspend operator fun invoke(challenge: Challenge) = challengeRepository.addChallenge(challenge)
}