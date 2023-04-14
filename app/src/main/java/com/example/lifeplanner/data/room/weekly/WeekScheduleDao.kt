package com.example.lifeplanner.data.room.weekly

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeekScheduleDao {
    @Insert
    fun insertSchedule(schedule: WeekSchedule)

    @Query("SELECT * FROM Schedule")
    fun getAll(): List<WeekSchedule>

    @Query("DELETE FROM Schedule WHERE id == (:id)")
    fun deleteScheduleById(id: Int)
}