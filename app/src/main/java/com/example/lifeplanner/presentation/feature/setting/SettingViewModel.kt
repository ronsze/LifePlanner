package com.example.lifeplanner.presentation.feature.setting

import androidx.lifecycle.LiveData
import com.example.lifeplanner.presentation.base.BaseViewModel
import com.example.lifeplanner.presentation.base.SingleLiveEvent
import javax.inject.Inject

class SettingViewModel @Inject constructor(

): BaseViewModel() {
    private val _initViewEvent = SingleLiveEvent<Void>()
    val initViewEvent: LiveData<Void> = _initViewEvent

    fun loadData() {
        _initViewEvent.call()
    }
}