package kr.sdbk.domain.usecase

import kr.sdbk.domain.model.schedule.Schedule

interface UpdateSchedulesUseCase {
    suspend operator fun invoke(
        schedules: List<Schedule>
    ): Result<Unit>
}