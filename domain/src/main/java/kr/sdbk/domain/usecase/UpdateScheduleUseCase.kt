package kr.sdbk.domain.usecase

import kr.sdbk.domain.model.schedule.Schedule

interface UpdateScheduleUseCase {
    suspend operator fun invoke(
        schedule: Schedule
    ): Result<Unit>
}