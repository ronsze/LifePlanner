package usecase.schedule

import model.schedule.Schedule

interface UpdateScheduleUseCase {
    suspend operator fun invoke(
        schedule: Schedule,
        onSuccess: () -> Unit,
        onFailure: (Throwable) -> Unit
    )
}