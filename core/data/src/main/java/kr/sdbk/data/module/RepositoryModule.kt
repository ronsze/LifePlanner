package kr.sdbk.data.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.sdbk.data.repository.schedule.LocalScheduleDataSource
import kr.sdbk.data.repository.schedule.ScheduleRepositoryImpl
import kr.sdbk.data.room.dao.ScheduleDao
import repository.ScheduleRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun providesScheduleRepository(
        dataSource: LocalScheduleDataSource
    ): ScheduleRepository = ScheduleRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun providesLocalScheduleDataSource(
        scheduleDao: ScheduleDao
    ): LocalScheduleDataSource = LocalScheduleDataSource(scheduleDao)
}