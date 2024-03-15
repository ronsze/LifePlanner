package kr.sdbk.data.usecase.schedule

import kr.sdbk.domain.repository.schedule.ScheduleRepository
import kr.sdbk.domain.usecase.UpdateSchedulesUseCase

class UpdateSchedulesUseCaseImpl(
    private val repository: ScheduleRepository
): UpdateSchedulesUseCase {

}