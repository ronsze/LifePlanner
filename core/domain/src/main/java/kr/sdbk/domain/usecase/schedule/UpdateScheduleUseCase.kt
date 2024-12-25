package kr.sdbk.domain.usecase.schedule

import kr.sdbk.domain.model.schdule.Schedule

interface UpdateScheduleUseCase {
    suspend operator fun invoke(schedule: Schedule)
}