package kr.sdbk.lifeplanner.features.schedule

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.sdbk.domain.model.schedule.Schedule
import kr.sdbk.domain.usecase.DeleteScheduleUseCase
import kr.sdbk.domain.usecase.GetSchedulesUseCase
import kr.sdbk.domain.usecase.InsertScheduleUseCase
import kr.sdbk.domain.usecase.UpdateScheduleUseCase
import kr.sdbk.domain.usecase.UpdateSchedulesUseCase
import kr.sdbk.lifeplanner.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val insertScheduleUseCase: InsertScheduleUseCase,
    private val getSchedulesUseCase: GetSchedulesUseCase,
    private val updateScheduleUseCase: UpdateScheduleUseCase,
    private val updateSchedulesUseCase: UpdateSchedulesUseCase,
    private val deleteScheduleUseCase: DeleteScheduleUseCase
): BaseViewModel() {
    private val _currentList: MutableState<List<Schedule>> = mutableStateOf(listOf())
    val currentList: State<List<Schedule>> get() = _currentList

    init {
        loadData()
    }

    private fun loadData() {
        loadSchedules()
    }

    private fun insertSchedule(schedule: Schedule) {
        viewModelScope.launch {
            insertScheduleUseCase(schedule)
                .onSuccess {

                }.onFailure {}
        }
    }

    private fun loadSchedules() {
        viewModelScope.launch {
            getSchedulesUseCase()
                .onSuccess {
                    _currentList.value = it
                }.onFailure {}
        }
    }

    fun updateSchedule(schedule: Schedule) {
        viewModelScope.launch {
            updateScheduleUseCase(schedule)
                .onSuccess {

                }.onFailure {  }
        }
    }

    fun updateSchedules(schedules: List<Schedule>) {
        viewModelScope.launch {
            updateSchedulesUseCase(schedules)
                .onSuccess {

                }.onFailure {  }
        }
    }

    fun deleteSchedule(schedule: Schedule) {
        viewModelScope.launch {
            deleteScheduleUseCase(schedule)
                .onSuccess {
                    loadSchedules()
                }.onFailure {  }
        }
    }
}