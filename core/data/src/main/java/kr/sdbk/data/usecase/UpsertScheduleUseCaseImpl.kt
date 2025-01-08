package kr.sdbk.data.usecase

import kr.sdbk.data.repository.ScheduleRepository
import kr.sdbk.domain.model.schdule.Schedule
import kr.sdbk.domain.usecase.schedule.UpsertScheduleUseCase

class UpsertScheduleUseCaseImpl(
    private val repository: ScheduleRepository
) : UpsertScheduleUseCase {
    override suspend fun invoke(schedule: Schedule) = repository.upsertSchedule(schedule)
}