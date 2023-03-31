package com.example.lifeplanner.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeekScheduleDao {
    @Query("SELECT * FROM Schedule")
    fun getAll(): List<WeekSchedule>

    @Insert
    fun insertSchedule(schedule: WeekSchedule)

    @Query("DELETE FROM Schedule WHERE id == (:id)")
    fun deleteScheduleById(id: Int)
}