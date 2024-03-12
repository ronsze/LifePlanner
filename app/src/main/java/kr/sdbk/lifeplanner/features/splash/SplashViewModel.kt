package kr.sdbk.lifeplanner.features.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.sdbk.lifeplanner.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(): BaseViewModel() {
    private val _viewState = mutableStateOf<SplashState>(SplashState.Nothing)
    val viewState: State<SplashState> get() = _viewState

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            delay(2000)
            _viewState.value = SplashState.Loaded
        }
    }

    sealed class SplashState {
        data object Nothing: SplashState()
        data object Loaded: SplashState()
    }
}