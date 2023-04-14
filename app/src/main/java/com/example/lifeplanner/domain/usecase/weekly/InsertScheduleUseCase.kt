package com.example.lifeplanner.domain.usecase.weekly

import com.example.lifeplanner.data.room.weekly.WeekSchedule
import com.example.lifeplanner.domain.repository.weekly.WeekRepository

class InsertScheduleUseCase(private val weekRepository: WeekRepository) {
    fun execute(schedule: WeekSchedule) = weekRepository.insertSchedule(schedule)
}