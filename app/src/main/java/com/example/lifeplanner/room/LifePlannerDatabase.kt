package com.example.lifeplanner.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WeekSchedule::class], version = 1)
abstract class LifePlannerDatabase: RoomDatabase() {
    abstract fun weekScheduleDao() : WeekScheduleDao
}