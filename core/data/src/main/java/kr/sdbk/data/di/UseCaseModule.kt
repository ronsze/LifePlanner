package kr.sdbk.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.sdbk.data.repository.ScheduleRepository
import kr.sdbk.data.usecase.DeleteScheduleUseCaseImpl
import kr.sdbk.data.usecase.GetAllScheduleUseCaseImpl
import kr.sdbk.data.usecase.InsertScheduleUseCaseImpl
import kr.sdbk.data.usecase.UpdateScheduleUseCaseImpl
import kr.sdbk.domain.usecase.schedule.DeleteScheduleUseCase
import kr.sdbk.domain.usecase.schedule.GetAllScheduleUseCase
import kr.sdbk.domain.usecase.schedule.InsertScheduleUseCase
import kr.sdbk.domain.usecase.schedule.UpdateScheduleUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun providesInsertScheduleUseCase(repository: ScheduleRepository): InsertScheduleUseCase = InsertScheduleUseCaseImpl(repository)

    @Provides
    @Singleton
    fun providesGetAllScheduleUseCase(repository: ScheduleRepository): GetAllScheduleUseCase = GetAllScheduleUseCaseImpl(repository)

    @Provides
    @Singleton
    fun providesUpdateAllScheduleUseCase(repository: ScheduleRepository): UpdateScheduleUseCase = UpdateScheduleUseCaseImpl(repository)

    @Provides
    @Singleton
    fun providesDeleteAlLScheduleUseCase(repository: ScheduleRepository): DeleteScheduleUseCase = DeleteScheduleUseCaseImpl(repository)
}