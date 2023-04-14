package com.example.lifeplanner.domain.repository.weekly

import com.example.lifeplanner.data.room.weekly.WeekSchedule

interface WeekRepository {
    fun insertSchedule(schedule: WeekSchedule)
    fun getAllSchedule(): List<WeekSchedule>
    fun deleteSchedule(id: Int)
}