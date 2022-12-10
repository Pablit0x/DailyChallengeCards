package com.pa.dailychallengecards.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.model.ChallengeStatus
import kotlinx.coroutines.flow.Flow


@Dao
interface ChallengeDao {

    @Query("SELECT * FROM challenge WHERE status ='Idle' ORDER BY RANDOM() LIMIT 3")
    fun getDailySelection(): Flow<List<Challenge>>

    @Query("UPDATE challenge SET status=:challengeStatus WHERE id =:id")
    suspend fun updateChallengeStatus(id : Int, challengeStatus: ChallengeStatus)

    @Query("SELECT * FROM challenge WHERE status =:challengeStatus")
    fun getChallengesByChallengeStatus(challengeStatus: ChallengeStatus) : Flow<List<Challenge>>

    // Admin queries

    @Query("SELECT * FROM challenge")
    fun getAllChallenges(): Flow<List<Challenge>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addChallenge(challenge: Challenge)

    @Query("DELETE FROM challenge WHERE id =:challengeId")
    suspend fun deleteChallenge(challengeId : Int)
}