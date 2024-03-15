package kr.sdbk.data.repository.schedule

import kr.sdbk.domain.model.schedule.Schedule
import kr.sdbk.domain.repository.schedule.ScheduleRepository

class ScheduleRepositoryImpl(
    private val localDataSource: LocalScheduleDataSource
): ScheduleRepository {
    override suspend fun insertSchedule(schedule: Schedule) = localDataSource.insertSchedule(schedule)
    override suspend fun getSchedules(): List<Schedule> = localDataSource.getSchedules()
    override suspend fun updateSchedule(schedule: Schedule) = localDataSource.updateSchedule(schedule)
    override suspend fun updateSchedules(schedules: List<Schedule>) = localDataSource.updateSchedules(schedules)
    override suspend fun deleteSchedule(schedule: Schedule) = localDataSource.deleteSchedule(schedule)
}