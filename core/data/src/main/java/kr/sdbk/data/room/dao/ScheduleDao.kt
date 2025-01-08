package kr.sdbk.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kr.sdbk.data.room.entity.ScheduleEntity

@Dao
interface ScheduleDao {
    @Upsert
    suspend fun upsertSchedule(scheduleEntity: ScheduleEntity)

    @Query("SELECT * FROM schedule")
    suspend fun getAllSchedule(): List<ScheduleEntity>

    @Delete
    suspend fun deleteSchedule(schedule: ScheduleEntity)
}