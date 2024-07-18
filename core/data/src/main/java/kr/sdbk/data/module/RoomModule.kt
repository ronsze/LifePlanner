package kr.sdbk.data.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.sdbk.data.room.dao.ScheduleDao
import kr.sdbk.data.room.database.LifePlannerDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): LifePlannerDatabase = Room.databaseBuilder(
        context,
        LifePlannerDatabase::class.java,
        "lifeplanner-database"
    ).build()

    @Provides
    @Singleton
    fun providesScheduleDao(database: LifePlannerDatabase): ScheduleDao = database.scheduleDao()
}