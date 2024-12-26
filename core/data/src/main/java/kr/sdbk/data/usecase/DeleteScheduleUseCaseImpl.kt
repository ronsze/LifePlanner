package kr.sdbk.data.usecase

import kr.sdbk.data.repository.ScheduleRepository
import kr.sdbk.domain.model.schdule.Schedule
import kr.sdbk.domain.usecase.schedule.DeleteScheduleUseCase

class DeleteScheduleUseCaseImpl(
    private val repository: ScheduleRepository
) : DeleteScheduleUseCase {
    override suspend fun invoke(schedule: Schedule) = repository.deleteSchedule(schedule)
}