package com.pa.dailychallengecards.domain.use_case

data class ChallengeUseCases(
    val getDailySelection: GetDailySelection,
    val getCompletedChallenges: GetCompletedChallenges,
    val updateChallengeStatus: UpdateChallengeStatus,
    val rollDailySelection: RollDailySelection,
    val resetDailySelection: ResetDailySelection,
    val addChallenge: AddChallenge,
    val deleteAllChallenges: DeleteAllChallenges
)