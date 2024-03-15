package kr.sdbk.lifeplanner.features.schedule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.domain.model.DayOfWeek
import kr.sdbk.lifeplanner.base.BaseFragment
import kr.sdbk.lifeplanner.ui.view.TabLayout

@AndroidEntryPoint
class ScheduleFragment: BaseFragment<ScheduleViewModel>() {
    override val viewModel: ScheduleViewModel by viewModels()

    @Composable
    override fun InitView() {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TabLayout(
                items = DayOfWeek.entries.map { it.name },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            )
            MainView(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }

    @Composable
    private fun MainView(
        modifier: Modifier
    ) {
        Box(
            modifier = modifier
        )
    }
}