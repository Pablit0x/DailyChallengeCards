package com.pa.dailychallengecards.domain.model.converter

import androidx.room.TypeConverter
import com.pa.dailychallengecards.R
import com.pa.dailychallengecards.domain.model.ChallengeDifficulty

class ChallengeDifficultyConverter {

    @TypeConverter
    fun fromChallengeDifficulty(challengeDifficulty: ChallengeDifficulty) : Int{
        return challengeDifficulty.difficultyIdentifierIcon
    }

    @TypeConverter
    fun fromInt(challengeDifficulty: Int) : ChallengeDifficulty{
        val difficulty = when (challengeDifficulty) {
            R.drawable.easy_challenge -> ChallengeDifficulty.EASY
            R.drawable.medium_challenge -> ChallengeDifficulty.MEDIUM
            R.drawable.hard_challenge -> ChallengeDifficulty.HARD
            else -> ChallengeDifficulty.EASY
        }
        return difficulty
    }
}