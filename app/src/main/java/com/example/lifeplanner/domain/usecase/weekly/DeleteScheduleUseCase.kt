package com.example.lifeplanner.domain.usecase.weekly

import com.example.lifeplanner.domain.repository.weekly.WeekRepository

class DeleteScheduleUseCase(private val weekRepository: WeekRepository) {
    fun execute(id: Int) = weekRepository.deleteSchedule(id)
}