package kr.sdbk.data.usecase.schedule

import kr.sdbk.domain.model.schedule.Schedule
import kr.sdbk.domain.repository.schedule.ScheduleRepository
import kr.sdbk.domain.usecase.GetSchedulesUseCase

class GetSchedulesUseCaseImpl(
    private val repository: ScheduleRepository
): GetSchedulesUseCase {
    override suspend fun invoke(): Result<List<Schedule>> = runCatching { repository.getSchedules() }
}