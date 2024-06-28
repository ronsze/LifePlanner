package kr.sdbk.data.usecase.schedule

import model.schedule.Schedule
import repository.ScheduleRepository
import usecase.schedule.InsertScheduleUseCase

class InsertSchedulesUseCaseImpl(
    private val repository: ScheduleRepository,
): InsertScheduleUseCase {
    override suspend fun invoke(
        schedule: Schedule,
        onSuccess: () -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val res = runCatching { repository.insertSchedule(schedule) }
        res.onSuccess { onSuccess() }
        res.onFailure(onFailure)
    }
}