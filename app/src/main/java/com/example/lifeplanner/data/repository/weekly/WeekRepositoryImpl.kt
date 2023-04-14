package com.example.lifeplanner.data.repository.weekly

import com.example.lifeplanner.data.room.weekly.WeekSchedule
import com.example.lifeplanner.domain.repository.weekly.WeekRepository

class WeekRepositoryImpl(private val localWeekDataSource: LocalWeekDataSource): WeekRepository {
    override fun insertSchedule(schedule: WeekSchedule) = localWeekDataSource.insertSchedule(schedule)

    override fun getAllSchedule(): List<WeekSchedule> = localWeekDataSource.getAllSchedule()

    override fun deleteSchedule(id: Int) = localWeekDataSource.deleteSchedule(id)
}