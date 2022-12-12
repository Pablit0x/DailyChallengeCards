package com.pa.dailychallengecards.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.model.converters.ChallengeDifficultyConverter
import com.pa.dailychallengecards.domain.model.converters.ChallengeStatusConverter

@Database(
    entities = [Challenge::class],
    version = 1
)

@TypeConverters(ChallengeDifficultyConverter::class, ChallengeStatusConverter::class)
abstract class ChallengeDatabase : RoomDatabase() {
    abstract val challengeDao: ChallengeDao

    companion object {
        const val DATABASE_NAME = "challenges_db"
    }
}