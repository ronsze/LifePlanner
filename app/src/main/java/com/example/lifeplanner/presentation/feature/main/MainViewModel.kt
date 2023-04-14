package com.example.lifeplanner.presentation.feature.main

import android.content.SharedPreferences
import android.view.Display.Mode
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import com.example.lifeplanner.presentation.base.BaseViewModel
import com.example.lifeplanner.presentation.base.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sp: SharedPreferences
): BaseViewModel() {
    companion object {
        private const val MODE = "mode"
    }

    enum class ClockMode(val mode: String) {
        WEEKLY("weekly"), DAILY("daily")
    }

    lateinit var mode: String

    private val _initViewEvent = SingleLiveEvent<Void>()
    val initViewEvent: LiveData<Void> get() = _initViewEvent

    fun loadData() {
        mode = sp.getString(MODE, ClockMode.WEEKLY.mode) ?: ClockMode.WEEKLY.mode
        _initViewEvent.call()
    }

    fun changeMode() {
        mode =
            if (mode == ClockMode.WEEKLY.mode) ClockMode.DAILY.mode
            else ClockMode.WEEKLY.mode

        sp.edit {
            putString(MODE, mode)
        }
    }
}