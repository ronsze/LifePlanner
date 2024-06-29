package kr.sdbk.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.sdbk.data.room.dao.ScheduleDao
import kr.sdbk.data.room.entity.ScheduleEntity

@Database(entities = [ScheduleEntity::class], version = 1)
abstract class LifePlannerDatabase: RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
}