package com.pa.dailychallengecards.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.model.ChallengeStatus
import kotlinx.coroutines.flow.Flow


@Dao
interface ChallengeDao {

    @Query("UPDATE challenge SET status =:desiredStatus WHERE id IN ( SELECT id FROM challenge WHERE status = :initialStatus ORDER BY RANDOM() LIMIT :numberOfChallenges )")
    suspend fun rollDailySelection(initialStatus: ChallengeStatus, desiredStatus: ChallengeStatus, numberOfChallenges : Int)

    @Query("SELECT * FROM challenge WHERE status =:activeStatus")
    fun getDailySelection(activeStatus: ChallengeStatus): Flow<List<Challenge>>

    @Query("UPDATE challenge SET status =:idle WHERE status = :active OR status =:completed")
    suspend fun resetChallengesStatus(active : ChallengeStatus, completed : ChallengeStatus, idle : ChallengeStatus)

    @Query("UPDATE challenge SET status=:desiredStatus WHERE id =:id")
    suspend fun updateChallengeStatus(id : Int, desiredStatus: ChallengeStatus)

    @Query("SELECT * FROM challenge WHERE status =:completedStatus")
    fun getCompletedChallenges(completedStatus: ChallengeStatus) : Flow<List<Challenge>>

    @Insert
    fun addChallenge(challenge: Challenge)
}