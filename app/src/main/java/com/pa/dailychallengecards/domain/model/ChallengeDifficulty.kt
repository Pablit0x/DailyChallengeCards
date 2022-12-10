package com.pa.dailychallengecards.domain.model

import com.pa.dailychallengecards.R

sealed class ChallengeDifficulty(val difficultyIdentifierIcon: Int) {
    object EASY : ChallengeDifficulty(difficultyIdentifierIcon = R.drawable.easy_challenge)

    object MEDIUM : ChallengeDifficulty(difficultyIdentifierIcon = R.drawable.medium_challenge)

    object HARD : ChallengeDifficulty(difficultyIdentifierIcon = R.drawable.hard_challenge)
}
