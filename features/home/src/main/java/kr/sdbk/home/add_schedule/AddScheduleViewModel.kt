package kr.sdbk.home.add_schedule

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.sdbk.core_common.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AddScheduleViewModel @Inject constructor(
    application: Application
): BaseViewModel(application) {
}