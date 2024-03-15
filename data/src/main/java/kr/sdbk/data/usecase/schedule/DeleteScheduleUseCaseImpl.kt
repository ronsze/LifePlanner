package kr.sdbk.data.usecase.schedule

import kr.sdbk.domain.repository.schedule.ScheduleRepository
import kr.sdbk.domain.usecase.DeleteScheduleUseCase

class DeleteScheduleUseCaseImpl(
    private val repository: ScheduleRepository
): DeleteScheduleUseCase {
}