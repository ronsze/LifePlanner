package com.example.lifeplanner.presentation.feature.daily

import androidx.lifecycle.LiveData
import com.example.lifeplanner.presentation.base.BaseViewModel
import com.example.lifeplanner.presentation.base.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DailyContainerViewModel @Inject constructor(

): BaseViewModel() {
    private val _initViewEvent = SingleLiveEvent<Void>()
    val initViewEvent: LiveData<Void> = _initViewEvent

    private var currentTime = ""
    private var selectedTime = ""

    fun loadData() {
        setTime()
        _initViewEvent.call()
    }

    private fun setTime() {
        currentTime = ""
        selectedTime = currentTime
    }
}