package com.example.lifeplanner.data.repository.weekly

import com.example.lifeplanner.data.room.LifePlannerDatabase
import com.example.lifeplanner.data.room.weekly.WeekSchedule
import com.example.lifeplanner.domain.repository.weekly.WeekRepository

class LocalWeekDataSource(lifePlannerDatabase: LifePlannerDatabase): WeekRepository {
    private val dao = lifePlannerDatabase.weekScheduleDao()

    override fun insertSchedule(schedule: WeekSchedule) = dao.insertSchedule(schedule)

    override fun getAllSchedule(): List<WeekSchedule> = dao.getAll()

    override fun deleteSchedule(id: Int) = dao.deleteScheduleById(id)
}