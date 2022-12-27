package com.pa.dailychallengecards.domain.model.converters

import androidx.room.TypeConverter
import com.pa.dailychallengecards.domain.model.ChallengeStatus

class ChallengeStatusConverter {

    @TypeConverter
    fun fromChallengeStatus(challengeStatus: ChallengeStatus): String {
        return challengeStatus.name
    }

    @TypeConverter
    fun fromString(challengeStatus: String): ChallengeStatus {
        val status = when (challengeStatus) {
            "Idle" -> ChallengeStatus.Idle
            "Active" -> ChallengeStatus.Active
            "Completed" -> ChallengeStatus.Completed
            "Selected" -> ChallengeStatus.Selected
            else -> ChallengeStatus.Idle
        }
        return status
    }
}