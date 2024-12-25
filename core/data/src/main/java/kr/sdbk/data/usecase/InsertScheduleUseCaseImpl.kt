package kr.sdbk.data.usecase

import kr.sdbk.data.repository.ScheduleRepository
import kr.sdbk.domain.model.schdule.Schedule
import kr.sdbk.domain.usecase.schedule.InsertScheduleUseCase

class InsertScheduleUseCaseImpl(
    private val repository: ScheduleRepository
) : InsertScheduleUseCase {
    override suspend fun invoke(schedule: Schedule) = repository.insertSchedule(schedule)
}