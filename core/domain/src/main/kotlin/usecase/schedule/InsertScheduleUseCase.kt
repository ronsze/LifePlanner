package usecase.schedule

import model.schedule.Schedule

interface InsertScheduleUseCase {
    suspend operator fun invoke(
        schedule: Schedule,
        onSuccess: () -> Unit,
        onFailure: (Throwable) -> Unit
    )
}