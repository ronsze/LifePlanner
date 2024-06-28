package kr.sdbk.data.repository.schedule

import kr.sdbk.data.mapper.ScheduleMapper.fromModel
import kr.sdbk.data.mapper.ScheduleMapper.toModel
import kr.sdbk.data.room.dao.ScheduleDao
import model.schedule.Schedule
import repository.ScheduleRepository

class LocalScheduleDataSource(
    private val scheduleDao: ScheduleDao
): ScheduleRepository {
    override suspend fun insertSchedule(schedule: Schedule) = scheduleDao.insertSchedule(schedule.fromModel())
    override suspend fun getAllSchedules(): List<Schedule> = scheduleDao.getAllSchedule().map { it.toModel() }
    override suspend fun updateSchedule(schedule: Schedule) = scheduleDao.updateSchedule(schedule.fromModel())
    override suspend fun deleteSchedule(schedule: Schedule) = scheduleDao.deleteSchedule(schedule.fromModel())
}