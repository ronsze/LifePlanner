package com.example.lifeplanner.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lifeplanner.data.room.weekly.WeekSchedule
import com.example.lifeplanner.data.room.weekly.WeekScheduleDao

@Database(entities = [WeekSchedule::class], version = 1, exportSchema = false)
abstract class LifePlannerDatabase: RoomDatabase() {
    abstract fun weekScheduleDao() : WeekScheduleDao
}