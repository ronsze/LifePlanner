package usecase.schedule

import model.schedule.Schedule

interface GetAllScheduleUseCase {
    suspend operator fun invoke(
        onSuccess: (List<Schedule>) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}