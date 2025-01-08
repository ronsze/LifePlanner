package kr.sdbk.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.sdbk.data.repository.ScheduleRepository
import kr.sdbk.data.usecase.DeleteScheduleUseCaseImpl
import kr.sdbk.data.usecase.GetAllScheduleUseCaseImpl
import kr.sdbk.data.usecase.UpsertScheduleUseCaseImpl
import kr.sdbk.domain.usecase.schedule.DeleteScheduleUseCase
import kr.sdbk.domain.usecase.schedule.GetAllScheduleUseCase
import kr.sdbk.domain.usecase.schedule.UpsertScheduleUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun providesUpsertScheduleUseCase(repository: ScheduleRepository): UpsertScheduleUseCase = UpsertScheduleUseCaseImpl(repository)

    @Provides
    @Singleton
    fun providesGetAllScheduleUseCase(repository: ScheduleRepository): GetAllScheduleUseCase = GetAllScheduleUseCaseImpl(repository)

    @Provides
    @Singleton
    fun providesDeleteAlLScheduleUseCase(repository: ScheduleRepository): DeleteScheduleUseCase = DeleteScheduleUseCaseImpl(repository)
}