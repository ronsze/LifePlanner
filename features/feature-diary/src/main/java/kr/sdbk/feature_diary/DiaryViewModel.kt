package kr.sdbk.feature_diary

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.sdbk.core_common.viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    application: Application
): BaseViewModel(
    application
) {

}