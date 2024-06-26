package kr.sdbk.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kr.sdbk.data.room.entity.ScheduleEntity
import java.time.DayOfWeek

@Dao
interface ScheduleDao {
    @Insert
    suspend fun addSchedule(schedule: ScheduleEntity)

    @Query("SELECT * FROM Schedule")
    suspend fun getAllSchedule(): List<ScheduleEntity>

    @Query("SELECT * FROM Schedule WHERE dayOfWeek = :dayOfWeek")
    suspend fun getScheduleByDayOfWeek(dayOfWeek: String): List<ScheduleEntity>

    @Update
    suspend fun updateSchedule(schedule: ScheduleEntity)

    @Delete
    suspend fun deleteSchedule(scheduleEntity: ScheduleEntity)
}