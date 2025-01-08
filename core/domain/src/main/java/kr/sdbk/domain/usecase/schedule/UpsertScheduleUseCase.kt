package kr.sdbk.domain.usecase.schedule

import kr.sdbk.domain.model.schdule.Schedule

interface UpsertScheduleUseCase {
    suspend operator fun invoke(
        schedule: Schedule
    )
}