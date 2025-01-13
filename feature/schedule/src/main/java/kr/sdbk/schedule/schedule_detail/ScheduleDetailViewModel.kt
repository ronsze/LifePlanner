package kr.sdbk.schedule.schedule_detail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.sdbk.common.base.BaseViewModel
import kr.sdbk.domain.model.schdule.Schedule
import kr.sdbk.domain.usecase.schedule.UpsertScheduleUseCase
import javax.inject.Inject

@HiltViewModel
class ScheduleDetailViewModel @Inject constructor(
    private val upsertScheduleUseCase: UpsertScheduleUseCase,
) : BaseViewModel() {
    private val _uiState: MutableStateFlow<ScheduleDetailUiState> = MutableStateFlow(ScheduleDetailUiState.View)
    val uiState get() = _uiState.asStateFlow()

    fun upsertSchedule(schedule: Schedule) {
        viewModelScope.launch {
            upsertScheduleUseCase(schedule)
            _uiState.emit(ScheduleDetailUiState.Updated)
        }
    }

    sealed interface ScheduleDetailUiState {
        data object View: ScheduleDetailUiState
        data object Updated: ScheduleDetailUiState
    }
}