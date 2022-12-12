package com.pa.dailychallengecards.domain.use_case

data class ChallengeUseCases(
    val getDailySelection: GetDailySelection,
    val getCompletedChallenges: GetCompletedChallenges,
    val updateChallengeStatus: UpdateChallengeStatus,
    val rollDailySelection: RollDailySelection,
    val resetChallengesStatus: ResetChallengesStatus,
    val addChallenge: AddChallenge
)