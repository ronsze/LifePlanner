package kr.sdbk.core_common.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    application: Application
): AndroidViewModel(application) {
    protected fun<T> MutableStateFlow<T>.set(value: T) = viewModelScope.launch { emit(value) }
    protected fun<T> MutableSharedFlow<T>.event(value: T) = viewModelScope.launch { emit(value) }
}