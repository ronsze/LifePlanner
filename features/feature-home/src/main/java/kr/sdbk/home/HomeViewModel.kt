package kr.sdbk.home

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kr.sdbk.core_common.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application
): BaseViewModel(application) {
    private val _list = mutableStateListOf("1")
    val list: SnapshotStateList<String> get() = _list

    fun loadData() {
        viewModelScope.launch {
            delay(1000)
            _list.add("2")
            delay(1000)
            _list.add("3")
            delay(1000)
            _list.remove("2")
            Log.e("qweqwe", "${_list}")
        }
    }
}