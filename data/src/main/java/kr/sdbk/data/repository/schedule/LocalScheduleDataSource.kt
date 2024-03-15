package kr.sdbk.data.repository.schedule

import kr.sdbk.data.mapper.ScheduleMapper.toData
import kr.sdbk.data.room.dao.ScheduleDao
import kr.sdbk.domain.model.schedule.Schedule
import kr.sdbk.domain.repository.schedule.ScheduleRepository

class LocalScheduleDataSource(
    private val scheduleDao: ScheduleDao
): ScheduleRepository {
    override suspend fun insertSchedule(schedule: Schedule) = scheduleDao.insert(schedule)
    override suspend fun getSchedules(): List<Schedule> = scheduleDao.getAll().map { it.toData() }
    override suspend fun updateSchedule(schedule: Schedule) = scheduleDao.update(schedule)
    override suspend fun updateSchedules(schedules: List<Schedule>) = scheduleDao.update(schedules)
    override suspend fun deleteSchedule(schedule: Schedule) = scheduleDao.delete(schedule)
}