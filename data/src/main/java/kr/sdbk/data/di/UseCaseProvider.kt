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
import kr.sdbk.domain.usecase.DeleteScheduleUseCase
import kr.sdbk.domain.usecase.GetSchedulesUseCase
import kr.sdbk.domain.usecase.InsertScheduleUseCase
import kr.sdbk.domain.usecase.UpdateScheduleUseCase
import kr.sdbk.domain.usecase.UpdateSchedulesUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseProvider {
    @Provides
    @Singleton
    fun providesInsertScheduleUseCase(repository: ScheduleRepository): InsertScheduleUseCase = InsertScheduleUseCaseImpl(repository)
    @Provides
    @Singleton
    fun providesGetSchedulesUseCase(repository: ScheduleRepository): GetSchedulesUseCase = GetSchedulesUseCaseImpl(repository)
    @Provides
    @Singleton
    fun providesUpdateScheduleUseCase(repository: ScheduleRepository): UpdateScheduleUseCase = UpdateScheduleUseCaseImpl(repository)
    @Provides
    @Singleton
    fun providesUpdateSchedulesUseCase(repository: ScheduleRepository): UpdateSchedulesUseCase = UpdateSchedulesUseCaseImpl(repository)
    @Provides
    @Singleton
    fun providesDeleteSchedulesUseCase(repository: ScheduleRepository): DeleteScheduleUseCase = DeleteScheduleUseCaseImpl(repository)
}