package kr.sdbk.schedule.schedule_detail

import android.annotation.SuppressLint
import android.widget.NumberPicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import kr.sdbk.common.ui.composable.BasePreview
import kr.sdbk.common.ui.composable.BaseText
import kr.sdbk.common.ui.composable.BasicToolbar
import kr.sdbk.common.ui.composable.BasicToolbarDefaults
import kr.sdbk.domain.model.schdule.AlarmType
import kr.sdbk.domain.model.schdule.DayOfWeek
import kr.sdbk.domain.model.schdule.Schedule

@Composable
fun ScheduleDetailView(
    schedule: Schedule?,
    onBackPressed: () -> Unit,
    viewModel: ScheduleDetailViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        var title by remember { mutableStateOf(schedule?.title ?: "") }
        var detail by remember { mutableStateOf(schedule?.detail ?: "") }
        val hour = remember { mutableStateOf(schedule?.hour ?: 0) }
        val minute = remember { mutableIntStateOf(schedule?.minute ?: 0) }
        val dayOfWeek = remember { schedule?.dayOfWeek ?: DayOfWeek.getCurrentDayOfWeek() }

        TitleBar(
            title = title,
            onTitleChanged = { title = it },
            onBackPressed = onBackPressed,
            onClickSave = {

            }
        )
        TimeSelectView(
            hour = hour,
            minute = minute
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

        var selectedAlarmType by remember { mutableStateOf(AlarmType.BELL) }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AlarmTypeView(AlarmType.BELL, selectedAlarmType == AlarmType.BELL) { selectedAlarmType = it }
            AlarmTypeView(AlarmType.VIBRATION, selectedAlarmType == AlarmType.VIBRATION ) { selectedAlarmType = it }
        }

        if (selectedAlarmType == AlarmType.BELL) {
            BellDetailSettingView()
        }
    }
}

@Composable
private fun RowScope.AlarmTypeView(
    type: AlarmType,
    isSelected: Boolean,
    onSelect: (AlarmType) -> Unit
) {
    val (borderColor, borderWidth) = if (isSelected) Color.Black to 1.5.dp else Color.LightGray to 1.dp
    Text(
        text = type.name,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .weight(1f)
            .height(45.dp)
            .border(borderWidth, borderColor, RoundedCornerShape(8.dp))
            .wrapContentHeight()
            .clickable { onSelect(type) }
    )
}

@Composable
private fun BellDetailSettingView() {

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
private fun InputAreaPreview() {
    InputAreaView(
        detail = "Hello",
        onDetailChanged = {}
    )
}