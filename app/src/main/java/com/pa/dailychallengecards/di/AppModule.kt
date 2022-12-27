package com.pa.dailychallengecards.di

import android.app.Application
import androidx.room.Room
import com.pa.dailychallengecards.data.data_source.ChallengeDatabase
import com.pa.dailychallengecards.data.repository.ChallengeRepositoryImpl
import com.pa.dailychallengecards.domain.notification.DailyNotificationService
import com.pa.dailychallengecards.domain.repository.ChallengeRepository
import com.pa.dailychallengecards.domain.use_case.*
import com.pa.dailychallengecards.util.AlarmManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideChallengeDatabase(context: Application): ChallengeDatabase {
        return Room.databaseBuilder(
            context,
            ChallengeDatabase::class.java,
            ChallengeDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideChallengeRepository(db: ChallengeDatabase): ChallengeRepository {
        return ChallengeRepositoryImpl(db.challengeDao)
    }

    @Provides
    @Singleton
    fun provideChallengeUseCases(repository: ChallengeRepository): ChallengeUseCases {
        return ChallengeUseCases(
            getDailySelection = GetDailySelection(challengeRepository = repository),
            getCompletedChallenges = GetCompletedChallenges(repository),
            updateChallengeStatus = UpdateChallengeStatus(repository),
            rollDailySelection = RollDailySelection(repository),
            resetDailySelection = ResetDailySelection(repository),
            addChallenge = AddChallenge(repository),
            deleteAllChallenges = DeleteAllChallenges(repository)
        )
    }


    @Singleton
    @Provides
    fun provideDailyNotificationService(
        context: Application
    ): DailyNotificationService {
        return DailyNotificationService(context)
    }

    @Singleton
    @Provides
    fun provideAlarmManager(
        context: Application
    ): AlarmManager {
        return AlarmManager(context)
    }
}