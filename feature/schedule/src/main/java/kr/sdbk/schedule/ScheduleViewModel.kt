package kr.sdbk.schedule

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
    private val _uiState: MutableStateFlow<ScheduleUiState> = MutableStateFlow(ScheduleUiState.Loading)
    val uiState get () = _uiState

    private val _scheduleList: MutableStateFlow<List<Schedule>> = MutableStateFlow(listOf())
    val scheduleList get() = _scheduleList.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            _uiState.emit(ScheduleUiState.Loading)
            delay(100)  // pullRefreshBox indicator가 멈추는 이슈로 delay 추가
            try {
                val res = withContext(Dispatchers.IO) { getAllScheduleUseCase() }
                _uiState.emit(ScheduleUiState.View)
                _scheduleList.emit(res)
            } catch (e: Exception) {
                Log.e("qweqwe", "${e.message}")
            }
        }
    }

    sealed interface ScheduleUiState {
        data object Loading: ScheduleUiState
        data object View: ScheduleUiState
    }
}