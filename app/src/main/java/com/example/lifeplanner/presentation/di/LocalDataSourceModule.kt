package com.example.lifeplanner.presentation.di

import com.example.lifeplanner.data.repository.weekly.LocalWeekDataSource
import com.example.lifeplanner.data.room.LifePlannerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {
    @Provides
    @Singleton
    fun provideLocalWeekDataSource(lifePlannerDatabase: LifePlannerDatabase): LocalWeekDataSource =
        LocalWeekDataSource(lifePlannerDatabase)
}