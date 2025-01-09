package kr.sdbk.schedule

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kr.sdbk.common.ui.composable.BasePreview
import kr.sdbk.common.ui.composable.BaseText
import kr.sdbk.domain.model.schdule.DayOfWeek
import kr.sdbk.domain.model.schdule.Schedule
import kr.sdbk.domain.model.schdule.ScheduleState

@Composable
fun ScheduleView(
    navigateToScheduleDetail: (Schedule?) -> Unit,
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE) {
                viewModel.loadData()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column {
        val scheduleList by viewModel.scheduleList.collectAsStateWithLifecycle()
        var selectedTabIndex by remember { mutableIntStateOf(DayOfWeek.getCurrentDayOfWeek().ordinal) }
        DayOfWeekTab(
            selectedTabIndex = selectedTabIndex,
            onSelectTab = {
                selectedTabIndex = it
            }
        )
        ButtonDefaults.buttonColors()

        val schedules = scheduleList.filter {
            it.dayOfWeek.ordinal == selectedTabIndex
        }.sortedWith(
            compareBy(
                { it.hour },
                { it.minute }
            )
        )
        LazyColumn(
            contentPadding = PaddingValues(top = 25.dp, bottom = 30.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier
                .padding(horizontal = 15.dp)
        ) {
            items(schedules) {
                ScheduleItem(
                    title = it.title,
                    hour = it.hour,
                    minute = it.minute,
                    state = it.state,
                    onClick = { navigateToScheduleDetail(it) }
                )
            }

            item {
                EmptyScheduleItem(
                    navigateToScheduleDetail = { navigateToScheduleDetail(null) }
                )
            }
        }
    }
}

@Composable
private fun DayOfWeekTab(
    selectedTabIndex: Int,
    onSelectTab: (Int) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
    ) {
        DayOfWeek.entries.forEachIndexed { i, v ->
            val isSelected = selectedTabIndex == i
            val (color, fontWeight) = if (isSelected) Color.Black to FontWeight.Bold else Color.LightGray to FontWeight.Normal
            BaseText(
                text = v.name,
                color = color,
                fontWeight = fontWeight,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 7.5.dp)
                    .clickable { onSelectTab(i) }
            )
        }
    }
}

@Composable
private fun ScheduleItem(
    title: String,
    hour: Int,
    minute: Int,
    state: ScheduleState,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
                .clickable { onClick() }
        ) {
            BaseText(
                text = title,
                fontSize = 15.sp,
                overflow = TextOverflow.Ellipsis
            )

            BaseText(
                text = "$hour:$minute",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }

        val (stateIcon, stateColor) = when (state) {
            ScheduleState.NOT_YET -> Icons.Filled.CheckCircle to Color.LightGray
            ScheduleState.COMPLETED -> Icons.Filled.CheckCircle to Color.Green
            ScheduleState.GONE -> Icons.Filled.Close to Color.Red
        }
        Image(
            imageVector = stateIcon,
            contentDescription = "",
            colorFilter = ColorFilter.tint(stateColor),
            modifier = Modifier
                .size(75.dp)
                .padding(5.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun EmptyScheduleItem(
    navigateToScheduleDetail: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .drawBehind {
                drawRoundRect(
                    color = Color.Black,
                    style = Stroke(
                        width = 10f,
                        pathEffect = PathEffect.dashPathEffect(
                            intervals = floatArrayOf(20f, 20f),
                            phase = 0f
                        ),
                    ),
                    cornerRadius = CornerRadius(15.dp.toPx())
                )
            }
            .clip(RoundedCornerShape(15.dp))
            .clickable { navigateToScheduleDetail() }
    ) {
        Image(
            imageVector = Icons.Filled.Add,
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.Center)
        )
    }
}

@BasePreview
@Composable
private fun DayOfWeekTabPreview() {
    DayOfWeekTab(
        0
    ) { }
}

@BasePreview
@Composable
private fun ScheduleItemPreview() {
    ScheduleItem(
        title = "Android meeting",
        hour = 12,
        minute = 30,
        state = ScheduleState.NOT_YET
    ) {}
}

@BasePreview
@Composable
private fun EmptyScheduleItemPreview() {
    EmptyScheduleItem {}
}