package kr.sdbk.data.usecase.schedule

import model.schedule.Schedule
import repository.ScheduleRepository
import usecase.schedule.GetAllScheduleUseCase

class GetAllSchedulesUseCaseImpl(
    private val repository: ScheduleRepository
): GetAllScheduleUseCase {
    override suspend fun invoke(
        onSuccess: (List<Schedule>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val res = runCatching { repository.getAllSchedules() }
        res.onSuccess(onSuccess)
        res.onFailure(onFailure)
    }
}