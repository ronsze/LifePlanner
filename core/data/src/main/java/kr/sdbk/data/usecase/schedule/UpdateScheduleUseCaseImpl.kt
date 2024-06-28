package kr.sdbk.data.usecase.schedule

import model.schedule.Schedule
import repository.ScheduleRepository
import usecase.schedule.UpdateScheduleUseCase

class UpdateScheduleUseCaseImpl(
    private val repository: ScheduleRepository
): UpdateScheduleUseCase {
    override suspend fun invoke(
        schedule: Schedule,
        onSuccess: () -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val res = runCatching { repository.updateSchedule(schedule) }
        res.onSuccess { onSuccess() }
        res.onFailure(onFailure)
    }
}