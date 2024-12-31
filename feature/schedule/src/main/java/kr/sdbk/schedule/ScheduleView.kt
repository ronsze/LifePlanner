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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kr.sdbk.common.ui.composable.BaseText

@Composable
fun ScheduleView(
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

    val schedules by viewModel.scheduleList.collectAsStateWithLifecycle()
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
                onClick = {}
            )
        }

        item {
            EmptyScheduleItem()
        }
    }
}

@Composable
private fun ScheduleItem(
    title: String,
    hour: Int,
    minute: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
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

        Image(
            imageVector = Icons.Filled.Menu,
            contentDescription = "",
            modifier = Modifier
                .size(75.dp)
                .padding(5.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun EmptyScheduleItem() {
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

@Preview
@Composable
private fun ScheduleItemPreview() {
    ScheduleItem(
        title = "Android meeting",
        hour = 12,
        minute = 30,
    ) {}
}

@Preview
@Composable
private fun EmptyScheduleItemPreview() {
    EmptyScheduleItem()
}