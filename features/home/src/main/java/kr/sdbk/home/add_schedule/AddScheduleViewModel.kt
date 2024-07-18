package kr.sdbk.home.add_schedule

import android.app.Application
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kr.sdbk.core_common.viewmodel.BaseViewModel
import kr.sdbk.data.module.IODispatcher
import model.schedule.Schedule
import usecase.schedule.InsertScheduleUseCase
import javax.inject.Inject

@HiltViewModel
class AddScheduleViewModel @Inject constructor(
    application: Application,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val insertScheduleUseCase: InsertScheduleUseCase,
): BaseViewModel(application) {
    fun insertSchedule(schedule: Schedule) {
        viewModelScope.launch(ioDispatcher) {
            insertScheduleUseCase(schedule, {

            }, handleBaseError {

            })
        }
    }
}