package kr.sdbk.schedule

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.sdbk.common.base.BaseViewModel
import kr.sdbk.domain.model.schdule.Schedule
import kr.sdbk.domain.usecase.schedule.GetAllScheduleUseCase
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val getAllScheduleUseCase: GetAllScheduleUseCase
): BaseViewModel() {
    private val _scheduleList: MutableStateFlow<List<Schedule>> = MutableStateFlow(listOf())
    val scheduleList get() = _scheduleList.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            try {
                val res = withContext(Dispatchers.IO) { getAllScheduleUseCase() }
                _scheduleList.emit(res)
            } catch (e: Exception) {
                Log.e("qweqwe", "${e.message}")
            }
        }
    }
}