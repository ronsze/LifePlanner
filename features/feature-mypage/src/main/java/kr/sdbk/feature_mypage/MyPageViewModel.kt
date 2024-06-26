package kr.sdbk.feature_mypage

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.sdbk.core_common.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    application: Application
): BaseViewModel(application) {
}