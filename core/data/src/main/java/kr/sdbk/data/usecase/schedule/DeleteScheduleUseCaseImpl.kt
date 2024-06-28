package kr.sdbk.data.usecase.schedule

import model.schedule.Schedule
import repository.ScheduleRepository
import usecase.schedule.DeleteScheduleUseCase

class DeleteScheduleUseCaseImpl(
    private val repository: ScheduleRepository
): DeleteScheduleUseCase {
    override suspend fun invoke(
        schedule: Schedule,
        onSuccess: () -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val res = runCatching { repository.deleteSchedule(schedule) }
        res.onSuccess { onSuccess() }
        res.onFailure(onFailure)
    }
}