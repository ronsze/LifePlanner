package kr.sdbk.data.usecase.schedule

import kr.sdbk.domain.model.schedule.Schedule
import kr.sdbk.domain.repository.schedule.ScheduleRepository
import kr.sdbk.domain.usecase.UpdateScheduleUseCase

class UpdateScheduleUseCaseImpl(
    private val repository: ScheduleRepository
): UpdateScheduleUseCase {
    override suspend fun invoke(schedule: Schedule): Result<Unit> = runCatching { repository.updateSchedule(schedule) }
}