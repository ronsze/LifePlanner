package kr.sdbk.data.repository

import kr.sdbk.domain.model.schdule.Schedule

interface ScheduleRepository {
    suspend fun upsertSchedule(schedule: Schedule)
    suspend fun getAllSchedule(): List<Schedule>
    suspend fun deleteSchedule(schedule: Schedule)
}