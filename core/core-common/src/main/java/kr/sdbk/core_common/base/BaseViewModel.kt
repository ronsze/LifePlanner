package kr.sdbk.core_common.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    application: Application
): AndroidViewModel(application) {
    protected fun<T> MutableStateFlow<T>.set(value: T) = viewModelScope.launch { emit(value) }
}