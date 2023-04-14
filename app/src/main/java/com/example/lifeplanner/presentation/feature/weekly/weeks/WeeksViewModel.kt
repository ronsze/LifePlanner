package com.example.lifeplanner.presentation.feature.weekly.weeks

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lifeplanner.data.room.weekly.WeekSchedule
import com.example.lifeplanner.domain.usecase.weekly.GetAllScheduleUseCase
import com.example.lifeplanner.domain.usecase.weekly.InsertScheduleUseCase
import com.example.lifeplanner.presentation.base.BaseViewModel
import com.example.lifeplanner.presentation.base.SingleLiveEvent
import com.example.lifeplanner.presentation.feature.weekly.WeekEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WeeksViewModel @Inject constructor(
    private val insertScheduleUseCase: InsertScheduleUseCase,
    private val getAllScheduleUseCase: GetAllScheduleUseCase
): BaseViewModel() {
    private val _initViewEvent = SingleLiveEvent<Void>()
    val initViewEvent: LiveData<Void> = _initViewEvent

    private val _scheduleListUpdateEvent = SingleLiveEvent<Void>()
    val scheduleListUpdateEvent: LiveData<Void> get() = _scheduleListUpdateEvent

    private val scheduleList = ArrayList<WeekSchedule>()

    fun loadData() {
        loadScheduleList()
    }

    private fun loadScheduleList() {
        CoroutineScope(Dispatchers.Main).launch {
            val list = withContext(Dispatchers.Default) { getAllScheduleUseCase.execute() }
            scheduleList.clear()
            scheduleList.addAll(list)
            _initViewEvent.call()
        }
    }

    fun addSchedule(schedule: WeekSchedule) {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) { insertScheduleUseCase.execute(schedule) }
            scheduleList.add(schedule)
            _scheduleListUpdateEvent.call()
        }
    }

    fun updateSchedule(schedule: WeekSchedule) {
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) { insertScheduleUseCase.execute(schedule) }
            val item = scheduleList.first { it.id == schedule.id }
            val i = scheduleList.indexOf(item)
            scheduleList[i] = schedule
            _scheduleListUpdateEvent.call()
        }
    }

    fun getScheduleList(index: Int): List<WeekSchedule> {
        return scheduleList.filter { it.dayOfWeekIndex == index }
    }
}