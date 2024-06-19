package kr.sdbk.feature_splash

import android.app.Application
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kr.sdbk.core_common.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    application: Application
): BaseViewModel(application) {
    private val _viewState: MutableStateFlow<SplashViewState> = MutableStateFlow(SplashViewState.Loading)
    val viewState: StateFlow<SplashViewState> get() = _viewState

    fun loadData() {
        viewModelScope.launch {
            delay(2000)
            _viewState.set(SplashViewState.DataLoaded)
        }
    }

    sealed class SplashViewState {
        data object Loading: SplashViewState()
        data object DataLoaded: SplashViewState()
    }
}