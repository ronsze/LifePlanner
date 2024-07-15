package kr.sdbk.home.home

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    private val _viewState: MutableStateFlow<HomeViewState> = MutableStateFlow(HomeViewState.Loading)
    val viewState get() = _viewState.asStateFlow()

    private val _scheduleList: MutableStateFlow<List<Schedule>> = MutableStateFlow(listOf())
    val scheduleList get() = _scheduleList.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        _viewState.set(HomeViewState.Loading)
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
            delay(1000)
            _scheduleList.set(
                listOf(
                    Schedule("", "tt", "", Time(15, 22), DayOfWeek.entries[0], ScheduleState.DISABLED)
                )
            )
            delay(1000)
            _scheduleList.set(
                listOf(
                    Schedule("", "tt", "", Time(15, 22), DayOfWeek.entries[0], ScheduleState.DISABLED),
                    Schedule("", "tt2", "", Time(15, 22), DayOfWeek.entries[0], ScheduleState.DISABLED)
                )
            )
            delay(1000)
            _scheduleList.set(
                listOf(
                    Schedule("", "tt", "", Time(15, 22), DayOfWeek.entries[0], ScheduleState.DISABLED),
                    Schedule("", "tt2", "", Time(15, 22), DayOfWeek.entries[0], ScheduleState.DISABLED),
                    Schedule("", "tt3", "", Time(15, 22), DayOfWeek.entries[0], ScheduleState.DISABLED)
                )
            )
            _viewState.set(HomeViewState.View)
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

    sealed interface HomeViewState {
        data object Loading: HomeViewState
        data object View: HomeViewState
    }
}