package com.example.lifeplanner.feature.weekly.weeks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lifeplanner.base.BaseViewModel
import com.example.lifeplanner.base.SingleLiveEvent
import com.example.lifeplanner.feature.weekly.WeekEnum
import com.example.lifeplanner.room.WeekSchedule
import com.example.lifeplanner.room.WeekScheduleDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WeeksViewModel @Inject constructor(
    private val weekScheduleDao: WeekScheduleDao
): BaseViewModel() {
    private val _initViewEvent = SingleLiveEvent<Void>()
    val initViewEvent: LiveData<Void> = _initViewEvent

    private val scheduleList = ArrayList<WeekSchedule>()

    fun loadData() {
        CoroutineScope(Dispatchers.Main).launch {
            val list = withContext(Dispatchers.Default) { weekScheduleDao.getAll() }
            scheduleList.clear()
            scheduleList.addAll(list)
            _initViewEvent.call()
        }
    }

    fun getScheduleList(index: Int): List<WeekSchedule> {
        return scheduleList.filter { it.dayOfWeek == WeekEnum.values()[index].day }
    }

    fun addSchedule(schedule: WeekSchedule) {
        scheduleList.add(schedule)
        viewModelScope.launch(Dispatchers.IO) {
            weekScheduleDao.insertSchedule(schedule)
        }
    }

    fun getDayInWeekIndex(): Int {
        return when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            Calendar.SUNDAY -> 0
            Calendar.MONDAY -> 1
            Calendar.TUESDAY -> 2
            Calendar.WEDNESDAY -> 3
            Calendar.THURSDAY -> 4
            Calendar.FRIDAY -> 5
            Calendar.SATURDAY -> 6
            else -> 0
        }
    }
}