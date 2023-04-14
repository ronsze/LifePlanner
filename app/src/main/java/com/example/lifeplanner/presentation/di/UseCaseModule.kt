package com.example.lifeplanner.presentation.di

import com.example.lifeplanner.domain.repository.weekly.WeekRepository
import com.example.lifeplanner.domain.usecase.weekly.DeleteScheduleUseCase
import com.example.lifeplanner.domain.usecase.weekly.GetAllScheduleUseCase
import com.example.lifeplanner.domain.usecase.weekly.InsertScheduleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideInsertScheduleUseCase(weekRepository: WeekRepository): InsertScheduleUseCase =
        InsertScheduleUseCase(weekRepository)


    @Provides
    @Singleton
    fun provideGetAllScheduleUseCase(weekRepository: WeekRepository): GetAllScheduleUseCase =
        GetAllScheduleUseCase(weekRepository)

    @Provides
    @Singleton
    fun provideDeleteScheduleUseCase(weekRepository: WeekRepository): DeleteScheduleUseCase =
        DeleteScheduleUseCase(weekRepository)
}