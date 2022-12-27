package com.pa.dailychallengecards.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Challenge(
    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val description: String,
    val difficulty: ChallengeDifficulty,
    val image: Int,
    val status: ChallengeStatus
)
