package kr.sdbk.domain.repository.schedule

import kr.sdbk.domain.model.schedule.Schedule

interface ScheduleRepository {
    suspend fun insertSchedule(schedule: Schedule)
    suspend fun getSchedules(): List<Schedule>
    suspend fun updateSchedule(schedule: Schedule)
    suspend fun updateSchedules(schedules: List<Schedule>)
    suspend fun deleteSchedule(schedule: Schedule)
}