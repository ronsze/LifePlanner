package kr.sdbk.schedule.schedule_detail

import android.annotation.SuppressLint
import android.widget.NumberPicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kr.sdbk.common.ui.composable.BasePreview
import kr.sdbk.common.ui.composable.BaseText
import kr.sdbk.common.ui.composable.BasicToolbar
import kr.sdbk.common.ui.composable.BasicToolbarDefaults
import kr.sdbk.domain.model.schdule.DayOfWeek
import kr.sdbk.domain.model.schdule.Schedule

@Composable
fun ScheduleDetailView(
    schedule: Schedule?,
    onBackPressed: () -> Unit,
    viewModel: ScheduleDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    if (uiState == ScheduleDetailViewModel.ScheduleDetailUiState.Updated) onBackPressed()

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        var title by remember { mutableStateOf(schedule?.title ?: "") }
        var detail by remember { mutableStateOf(schedule?.detail ?: "") }
        val hour = remember { mutableIntStateOf(schedule?.hour ?: 0) }
        val minute = remember { mutableIntStateOf(schedule?.minute ?: 0) }
        var dayOfWeek by remember { mutableStateOf(schedule?.dayOfWeek ?: DayOfWeek.getCurrentDayOfWeek()) }

        TitleBar(
            title = title,
            onTitleChanged = { title = it },
            onBackPressed = onBackPressed,
            onClickSave = {
                val newSchedule = Schedule(
                    created = schedule?.created ?: System.currentTimeMillis().toString(),
                    title = title,
                    detail = detail,
                    hour = hour.intValue,
                    minute = minute.intValue,
                    dayOfWeek = dayOfWeek
                )
                viewModel.upsertSchedule(newSchedule)
            }
        )
        TimeSelectView(
            hour = hour,
            minute = minute
        )
        DayOfWeekSelectView(
            dayOfWeek = dayOfWeek,
            onClickDayOfWeek = {
                dayOfWeek = it
            }
        )
        InputAreaView(
            detail = detail,
            onDetailChanged = { detail = it }
        )
    }
}

@Composable
private fun TitleBar(
    title: String,
    onTitleChanged: (String) -> Unit,
    onBackPressed: () -> Unit,
    onClickSave: () -> Unit
) {
    val isSaveAvailable = title.isNotEmpty()
    BasicToolbar(
        frontComposable = BasicToolbarDefaults.defaultIconComposable(
            image = Icons.AutoMirrored.Filled.ArrowBack,
            onClick = onBackPressed
        ),
        title = {
            BasicTextField(
                value = title,
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                ),
                onValueChange = onTitleChanged,
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        rearComposable = BasicToolbarDefaults.defaultIconComposable(
            image = Icons.Filled.Check,
            enabled = isSaveAvailable,
            onClick = onClickSave
        )
    )
}

@Composable
private fun TimeSelectView(
    hour: MutableState<Int>,
    minute: MutableState<Int>,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(Modifier.weight(1f))
        AndroidView(
            factory = {
                NumberPicker(it).apply {
                    minValue = 0
                    maxValue = 23
                    displayedValues = (minValue..maxValue).toList().map { "${String.format("%02d", it)}" }.toTypedArray()
                    setOnValueChangedListener { _, _, newVal ->
                        hour.value = newVal
                    }
                }
            },
            update = {
                it.value = hour.value
            }
        )

        AndroidView(
            factory = {
                NumberPicker(it).apply {
                    minValue = 0
                    maxValue = 59
                    displayedValues = (minValue..maxValue).toList().map { String.format("%02d", it) }.toTypedArray()
                    setOnValueChangedListener { _, oldVal, newVal ->
                        minute.value = newVal
                        if (oldVal == minValue && newVal == maxValue) hour.value -= 1
                        if (oldVal == maxValue && newVal == minValue) hour.value += 1
                    }
                }
            },
            update = {
                it.value = minute.value
            }
        )
        Spacer(Modifier.weight(1f))
    }
}

@Composable
private fun DayOfWeekSelectView(
    dayOfWeek: DayOfWeek,
    onClickDayOfWeek: (DayOfWeek) -> Unit
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .padding(horizontal = 16.dp)
    ) {
        DayOfWeek.entries.forEach {
            val isSelected = dayOfWeek == it
            val (color, weight) = if (isSelected) Color.Black to FontWeight.Bold else Color.LightGray to FontWeight.Normal
            BaseText(
                text = it.name,
                color = color,
                textAlign = TextAlign.Center,
                fontWeight = weight,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onClickDayOfWeek(it) }
            )
        }
    }
}

@Composable
private fun InputAreaView(
    detail: String,
    onDetailChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)
    ) {
        OutlinedTextField(
            value = detail,
            onValueChange = onDetailChanged,
            label = { BaseText(text = "Detail") },
            modifier = Modifier
                .fillMaxWidth()
                .height(105.dp)
        )
        Spacer(Modifier.height(15.dp))
    }
}

@BasePreview
@Composable
private fun TitleBarPreview() {
    TitleBar(
        title = "Android meeting",
        onTitleChanged = {},
        onBackPressed = {},
        onClickSave = {}
    )
}

@SuppressLint("UnrememberedMutableState")
@BasePreview
@Composable
private fun TimeSelectPreview() {
    TimeSelectView(
        hour = mutableStateOf(0),
        minute = mutableStateOf(0)
    )
}

@BasePreview
@Composable
private fun DayOfWeekSelectPreview() {
    DayOfWeekSelectView(
        DayOfWeek.getCurrentDayOfWeek()
    ) {}
}

@BasePreview
@Composable
private fun InputAreaPreview() {
    InputAreaView(
        detail = "Hello",
        onDetailChanged = {}
    )
}