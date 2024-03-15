package kr.sdbk.lifeplanner.features.schedule

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.sdbk.domain.model.schedule.Schedule
import kr.sdbk.lifeplanner.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(): BaseViewModel() {
    private val _currentList: MutableState<List<Schedule>> = mutableStateOf(listOf())
    val currentList: State<List<Schedule>> get() = _currentList

    init {
        loadData()
    }

    private fun loadData() {

    }
}