package kr.sdbk.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.sdbk.data.room.database.PlannerDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomProvider {
    @Provides
    @Singleton
    fun providesPlannerDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            PlannerDatabase::class.java,
            "planner"
        ).build()

    @Provides
    @Singleton
    fun providesScheduleDao(db: PlannerDatabase) = db.scheduleDao()
}