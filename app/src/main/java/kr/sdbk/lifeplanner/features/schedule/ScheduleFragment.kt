package kr.sdbk.lifeplanner.features.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.domain.model.DayOfWeek
import kr.sdbk.domain.model.schedule.Schedule
import kr.sdbk.lifeplanner.base.BaseFragment
import kr.sdbk.lifeplanner.ui.view.SimpleSpacer
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
            ScheduleList(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                items = viewModel.currentList.value
            )
        }
    }

    @Composable
    private fun ScheduleList(
        items: List<Schedule>,
        modifier: Modifier
    ) {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(top = 25.dp, bottom = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items.size) {
                if (it != 0) SimpleSpacer(25.dp)
                ScheduleItem(
                    item = items[it],
                    modifier = Modifier
                )
            }
        }
    }

    @Composable
    private fun ScheduleItem(
        item: Schedule,
        modifier: Modifier
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth(fraction = 0.8f)
                .height(65.dp)
                .background(Color.White)
                .border(1.dp, Color.Black, RoundedCornerShape(35.dp))
                .clip(RoundedCornerShape(35.dp))
        ) {
            Text(text = item.title)
            Text(text = item.detail, Modifier.offset(y = 25.dp))
        }
    }

    @Preview(widthDp = 350)
    @Composable
    private fun ScheduleItemPreview() {
        ScheduleItem(
            Schedule("3:15", 1, "Meeting", "esat metting for API"),
            modifier = Modifier
        )
    }
}