package kr.sdbk.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.sdbk.data.usecase.schedule.DeleteScheduleUseCaseImpl
import kr.sdbk.data.usecase.schedule.GetSchedulesUseCaseImpl
import kr.sdbk.data.usecase.schedule.InsertScheduleUseCaseImpl
import kr.sdbk.data.usecase.schedule.UpdateScheduleUseCaseImpl
import kr.sdbk.data.usecase.schedule.UpdateSchedulesUseCaseImpl
import kr.sdbk.domain.repository.schedule.ScheduleRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseProvider {
    @Provides
    @Singleton
    fun providesInsertScheduleUseCase(repository: ScheduleRepository) = InsertScheduleUseCaseImpl(repository)
    @Provides
    @Singleton
    fun providesGetSchedulesUseCase(repository: ScheduleRepository) = GetSchedulesUseCaseImpl(repository)
    @Provides
    @Singleton
    fun providesUpdateScheduleUseCase(repository: ScheduleRepository) = UpdateScheduleUseCaseImpl(repository)
    @Provides
    @Singleton
    fun providesUpdateSchedulesUseCase(repository: ScheduleRepository) = UpdateSchedulesUseCaseImpl(repository)
    @Provides
    @Singleton
    fun providesDeleteSchedulesUseCase(repository: ScheduleRepository) = DeleteScheduleUseCaseImpl(repository)
}