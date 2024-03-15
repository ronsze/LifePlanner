package kr.sdbk.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.sdbk.data.repository.schedule.LocalScheduleDataSource
import kr.sdbk.data.repository.schedule.ScheduleRepositoryImpl
import kr.sdbk.data.room.dao.ScheduleDao
import kr.sdbk.domain.repository.schedule.ScheduleRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryProvider {
    @Provides
    @Singleton
    fun providesScheduleRepository(localDataSource: LocalScheduleDataSource): ScheduleRepository = ScheduleRepositoryImpl(localDataSource)
    @Provides
    @Singleton
    fun providesLocalScheduleDataSource(scheduleDao: ScheduleDao): LocalScheduleDataSource = LocalScheduleDataSource(scheduleDao)
}