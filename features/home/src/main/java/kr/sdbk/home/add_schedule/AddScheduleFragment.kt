package kr.sdbk.home.add_schedule

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.core_common.context_view.BaseComposeFragment

@AndroidEntryPoint
class AddScheduleFragment: BaseComposeFragment<AddScheduleViewModel>() {
    override val fragmentViewModel: AddScheduleViewModel by viewModels()

    override fun afterComposed() {}

    @Composable
    override fun Root() {

    }
}