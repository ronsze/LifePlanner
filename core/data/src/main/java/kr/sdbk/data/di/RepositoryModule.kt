package kr.sdbk.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.sdbk.data.repository.ScheduleRepository
import kr.sdbk.data.repository.ScheduleRepositoryImpl
import kr.sdbk.data.room.dao.ScheduleDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun providesScheduleRepository(dao: ScheduleDao): ScheduleRepository = ScheduleRepositoryImpl(dao)
}