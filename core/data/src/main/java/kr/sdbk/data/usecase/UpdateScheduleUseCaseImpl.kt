package kr.sdbk.data.usecase

import kr.sdbk.data.repository.ScheduleRepository
import kr.sdbk.domain.model.schdule.Schedule
import kr.sdbk.domain.usecase.schedule.UpdateScheduleUseCase

class UpdateScheduleUseCaseImpl(
    private val repository: ScheduleRepository
) : UpdateScheduleUseCase {
    override suspend fun invoke(schedule: Schedule) = repository.updateSchedule(schedule)
}