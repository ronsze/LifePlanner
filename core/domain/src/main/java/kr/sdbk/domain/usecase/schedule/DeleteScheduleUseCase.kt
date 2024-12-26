package kr.sdbk.domain.usecase.schedule

import kr.sdbk.domain.model.schdule.Schedule

interface DeleteScheduleUseCase {
    suspend operator fun invoke(schedule: Schedule)
}