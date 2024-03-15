package kr.sdbk.data.usecase.schedule

import kr.sdbk.domain.model.schedule.Schedule
import kr.sdbk.domain.repository.schedule.ScheduleRepository
import kr.sdbk.domain.usecase.UpdateSchedulesUseCase

class UpdateSchedulesUseCaseImpl(
    private val repository: ScheduleRepository
): UpdateSchedulesUseCase {
    override suspend fun invoke(schedules: List<Schedule>): Result<Unit> = runCatching { repository.updateSchedules(schedules) }
}