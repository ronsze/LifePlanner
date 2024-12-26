package kr.sdbk.data.repository

import kr.sdbk.data.mapper.ScheduleMapper.toData
import kr.sdbk.data.mapper.ScheduleMapper.toEntity
import kr.sdbk.data.room.dao.ScheduleDao
import kr.sdbk.domain.model.schdule.Schedule

class ScheduleRepositoryImpl(
    private val localDataSource: ScheduleDao
) : ScheduleRepository {
    override suspend fun insertSchedule(schedule: Schedule) = localDataSource.insertSchedule(schedule.toEntity())
    override suspend fun getAllSchedule(): List<Schedule> = localDataSource.getAllSchedule().map { it.toData() }
    override suspend fun updateSchedule(schedule: Schedule) = localDataSource.updateSchedule(schedule.toEntity())
    override suspend fun deleteSchedule(schedule: Schedule) = localDataSource.deleteSchedule(schedule.toEntity())
}