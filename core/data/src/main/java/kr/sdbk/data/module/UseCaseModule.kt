package kr.sdbk.data.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.sdbk.data.usecase.schedule.InsertSchedulesUseCaseImpl
import repository.ScheduleRepository
import usecase.schedule.InsertScheduleUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun providesInsertScheduleUseCase(repository: ScheduleRepository): InsertScheduleUseCase = InsertSchedulesUseCaseImpl(repository)
}