package com.pa.dailychallengecards.di

import android.app.Application
import com.pa.dailychallengecards.data.data_source.ChallengeDatabase
import com.pa.dailychallengecards.domain.notification.DailyNotificationService
import com.pa.dailychallengecards.domain.repository.ChallengeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import androidx.room.Room
import com.pa.dailychallengecards.data.repository.ChallengeRepositoryImpl



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


    @Singleton
    @Provides
    fun provideDailyNotificationService(
        context: Application
    ): DailyNotificationService {
        return DailyNotificationService(context)
    }
}