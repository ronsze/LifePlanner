package kr.sdbk.data.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.sdbk.data.usecase.schedule.DeleteScheduleUseCaseImpl
import kr.sdbk.data.usecase.schedule.GetAllSchedulesUseCaseImpl
import kr.sdbk.data.usecase.schedule.InsertSchedulesUseCaseImpl
import kr.sdbk.data.usecase.schedule.UpdateScheduleUseCaseImpl
import repository.ScheduleRepository
import usecase.schedule.DeleteScheduleUseCase
import usecase.schedule.GetAllScheduleUseCase
import usecase.schedule.InsertScheduleUseCase
import usecase.schedule.UpdateScheduleUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun providesInsertScheduleUseCase(repository: ScheduleRepository): InsertScheduleUseCase = InsertSchedulesUseCaseImpl(repository)
    @Provides
    @Singleton
    fun providesGetAllSchedulesUseCase(repository: ScheduleRepository): GetAllScheduleUseCase = GetAllSchedulesUseCaseImpl(repository)
    @Provides
    @Singleton
    fun providesUpdateScheduleUseCase(repository: ScheduleRepository): UpdateScheduleUseCase = UpdateScheduleUseCaseImpl(repository)
    @Provides
    @Singleton
    fun providesDeleteScheduleUseCase(repository: ScheduleRepository): DeleteScheduleUseCase = DeleteScheduleUseCaseImpl(repository)
}