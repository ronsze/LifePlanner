package com.example.lifeplanner.presentation.di

import com.example.lifeplanner.data.repository.weekly.LocalWeekDataSource
import com.example.lifeplanner.data.repository.weekly.WeekRepositoryImpl
import com.example.lifeplanner.domain.repository.weekly.WeekRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideWeekRepository(localWeekDataSource: LocalWeekDataSource): WeekRepository =
        WeekRepositoryImpl(localWeekDataSource)
}