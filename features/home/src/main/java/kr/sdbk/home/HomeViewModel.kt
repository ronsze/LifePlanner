package kr.sdbk.home

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.sdbk.core_common.viewmodel.BaseViewModel
import kr.sdbk.data.module.IODispatcher
import model.schedule.Schedule
import model.schedule.ScheduleState
import model.schedule.Time
import usecase.schedule.DeleteScheduleUseCase
import usecase.schedule.GetAllScheduleUseCase
import usecase.schedule.InsertScheduleUseCase
import usecase.schedule.UpdateScheduleUseCase
import java.time.DayOfWeek
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val getAllSchedulesUseCase: GetAllScheduleUseCase,
    private val insertScheduleUseCase: InsertScheduleUseCase,
    private val updateScheduleUseCase: UpdateScheduleUseCase,
    private val deleteScheduleUseCase: DeleteScheduleUseCase
): BaseViewModel(application) {
    private val _scheduleList: SnapshotStateList<Schedule> = mutableStateListOf()
    val scheduleList: List<Schedule> get() = _scheduleList

    fun loadData() {
        loadAllSchedules()
    }

    fun loadAllSchedules() {
//        viewModelScope.launch(ioDispatcher) {
//            getAllSchedulesUseCase({
//                _scheduleList.set(it)
//            }, handleBaseError {
//
//            })
//        }
        viewModelScope.launch {
            val list = mutableListOf<Schedule>()
            delay(1000)
            _scheduleList.add(Schedule("", "tt", "", Time(15, 22), DayOfWeek.entries[0], ScheduleState.DISABLED))
            delay(1000)
            _scheduleList.add(Schedule("", "22", "", Time(15, 22), DayOfWeek.entries[0], ScheduleState.DISABLED))
            delay(1000)
            _scheduleList.add(Schedule("", "33", "", Time(15, 22), DayOfWeek.entries[0], ScheduleState.DISABLED))
        }
    }

    fun insertSchedule(schedule: Schedule) {
        viewModelScope.launch(ioDispatcher) {
            insertScheduleUseCase(schedule, {

            }, handleBaseError {

            })
        }
    }

    fun updateSchedule(schedule: Schedule) {
        viewModelScope.launch(ioDispatcher) {
            updateScheduleUseCase(schedule, {

            }, handleBaseError {

            })
        }
    }

    fun deleteSchedule(schedule: Schedule) {
        viewModelScope.launch(ioDispatcher) {
            deleteScheduleUseCase(schedule, {

            }, handleBaseError {

            })
        }
    }
}