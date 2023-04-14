package com.example.lifeplanner.domain.usecase.weekly

import com.example.lifeplanner.domain.repository.weekly.WeekRepository

class GetAllScheduleUseCase(private val weekRepository: WeekRepository) {
    fun execute() = weekRepository.getAllSchedule()
}