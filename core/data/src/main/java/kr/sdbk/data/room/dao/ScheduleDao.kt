package kr.sdbk.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kr.sdbk.data.room.entity.ScheduleEntity

@Dao
interface ScheduleDao {
    @Insert
    suspend fun insertSchedule(scheduleEntity: ScheduleEntity)

    @Query("SELECT * FROM schedule")
    suspend fun getAllSchedule(): List<ScheduleEntity>

    @Update
    suspend fun updateSchedule(schedule: ScheduleEntity)

    @Delete
    suspend fun deleteSchedule(schedule: ScheduleEntity)
}