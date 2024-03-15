package kr.sdbk.data.usecase.schedule

import kr.sdbk.domain.model.schedule.Schedule
import kr.sdbk.domain.repository.schedule.ScheduleRepository
import kr.sdbk.domain.usecase.InsertScheduleUseCase

class InsertScheduleUseCaseImpl(
    private val repository: ScheduleRepository
): InsertScheduleUseCase {
    override suspend fun invoke(schedule: Schedule) {

    }
}