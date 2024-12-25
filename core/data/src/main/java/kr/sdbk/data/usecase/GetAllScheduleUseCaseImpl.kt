package kr.sdbk.data.usecase

import kr.sdbk.data.repository.ScheduleRepository
import kr.sdbk.domain.model.schdule.Schedule
import kr.sdbk.domain.usecase.schedule.GetAllScheduleUseCase

class GetAllScheduleUseCaseImpl(
    private val repository: ScheduleRepository
) : GetAllScheduleUseCase {
    override suspend fun invoke(): List<Schedule> = repository.getAllSchedule()
}