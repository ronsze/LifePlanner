package kr.sdbk.data.usecase.schedule

import kr.sdbk.domain.model.schedule.Schedule
import kr.sdbk.domain.repository.schedule.ScheduleRepository
import kr.sdbk.domain.usecase.DeleteScheduleUseCase

class DeleteScheduleUseCaseImpl(
    private val repository: ScheduleRepository
): DeleteScheduleUseCase {
    override suspend fun invoke(schedule: Schedule): Result<Unit> = runCatching { repository.deleteSchedule(schedule) }
}