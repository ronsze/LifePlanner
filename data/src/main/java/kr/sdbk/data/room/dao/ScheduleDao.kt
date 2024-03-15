package kr.sdbk.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kr.sdbk.data.room.entity.ScheduleEntity
import kr.sdbk.domain.model.schedule.Schedule

@Dao
interface ScheduleDao {
    @Insert
    suspend fun insert(schedule: ScheduleEntity)

    @Query("SELECT * FROM schedule")
    suspend fun getAll(): List<ScheduleEntity>

    @Update
    suspend fun update(schedule: ScheduleEntity)

    @Update
    suspend fun update(schedules: List<ScheduleEntity>)

    @Delete
    suspend fun delete(schedule: ScheduleEntity)
}