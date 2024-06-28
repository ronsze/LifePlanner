package repository

import model.schedule.Schedule

interface ScheduleRepository {
    suspend fun insertSchedule(schedule: Schedule)
    suspend fun getAllSchedules(): List<Schedule>
    suspend fun updateSchedule(schedule: Schedule)
    suspend fun deleteSchedule(schedule: Schedule)
}