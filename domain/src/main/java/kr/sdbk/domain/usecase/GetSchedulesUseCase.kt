package kr.sdbk.domain.usecase

import kr.sdbk.domain.model.schedule.Schedule

interface GetSchedulesUseCase {
    suspend operator fun invoke(): Result<List<Schedule>>
}