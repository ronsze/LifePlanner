package com.example.lifeplanner.feature.weekly

import androidx.lifecycle.LiveData
import com.example.lifeplanner.base.BaseViewModel
import com.example.lifeplanner.base.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeeklyViewModel @Inject constructor(

): BaseViewModel() {
    private val _initViewEvent = SingleLiveEvent<Void>()
    val initViewEvent: LiveData<Void> = _initViewEvent

    fun loadData() {
        _initViewEvent.call()
    }
}