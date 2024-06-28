package kr.sdbk.data.repository.schedule

import model.schedule.Schedule
import repository.ScheduleRepository

class ScheduleRepositoryImpl(
    private val dataSource: LocalScheduleDataSource
): ScheduleRepository {
    override suspend fun insertSchedule(schedule: Schedule) = dataSource.insertSchedule(schedule)
    override suspend fun getAllSchedules(): List<Schedule> = dataSource.getAllSchedules()
    override suspend fun updateSchedule(schedule: Schedule) = dataSource.updateSchedule(schedule)
    override suspend fun deleteSchedule(schedule: Schedule) = dataSource.deleteSchedule(schedule)
}