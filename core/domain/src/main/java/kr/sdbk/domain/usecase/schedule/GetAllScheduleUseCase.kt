package kr.sdbk.domain.usecase.schedule

import kr.sdbk.domain.model.schdule.Schedule

interface GetAllScheduleUseCase {
    suspend operator fun invoke(): List<Schedule>
}