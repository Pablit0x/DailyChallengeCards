package com.pa.dailychallengecards.domain.model

sealed class ChallengeStatus(val name: String) {
    object Completed : ChallengeStatus(name = "Completed")

    object Active : ChallengeStatus(name = "Active")

    object Idle : ChallengeStatus(name = "Idle")

    object Selected : ChallengeStatus(name = "Selected")
}