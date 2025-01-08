package kr.sdbk.schedule.schedule_detail

import dagger.hilt.android.lifecycle.HiltViewModel
import kr.sdbk.common.base.BaseViewModel
import kr.sdbk.domain.usecase.schedule.UpsertScheduleUseCase
import javax.inject.Inject

@HiltViewModel
class ScheduleDetailViewModel @Inject constructor(
    private val upsertScheduleUseCase: UpsertScheduleUseCase,
) : BaseViewModel() {


}