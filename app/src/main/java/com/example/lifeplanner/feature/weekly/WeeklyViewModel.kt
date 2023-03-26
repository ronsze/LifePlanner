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

    private val dataList = ArrayList<Int>()

    fun loadData() {
        for (i in 0 .. 6) {
            dataList.add(0)
        }
        _initViewEvent.call()
    }
}