package com.example.lifeplanner.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.lifeplanner.data.room.LifePlannerDatabase
import com.example.lifeplanner.data.room.weekly.WeekScheduleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LifePlannerDatabase =
        Room.databaseBuilder(context, LifePlannerDatabase::class.java, "life_planner_database").build()

    @Provides
    @Singleton
    fun provideWeekScheduleDao(database: LifePlannerDatabase): WeekScheduleDao = database.weekScheduleDao()
}