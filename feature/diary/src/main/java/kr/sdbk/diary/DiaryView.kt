package kr.sdbk.diary

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.sdbk.common.TimeFunctions.convertMillisToDate
import kr.sdbk.common.TimeFunctions.getNextDay
import kr.sdbk.common.TimeFunctions.getPreviousDay
import kr.sdbk.common.TimeFunctions.getToday
import kr.sdbk.common.ui.composable.BasePreview
import kr.sdbk.common.ui.composable.BaseText

@Composable
fun DiaryView() {
    Column {
        var datePickerVisible by remember { mutableStateOf(false) }
        var currentDate by remember { mutableStateOf(getToday()) }
        DateView(
            date = currentDate,
            onClickDate = {
                datePickerVisible = true
            },
            onClickPrevious = {
                currentDate = getPreviousDay(currentDate)
            },
            onClickNext = {
                currentDate = getNextDay(currentDate)
            }
        )
        WritingView()
        DatePickerModal(
            datePickerVisible = datePickerVisible,
            onConfirm = {
                it?.run {
                    currentDate = convertMillisToDate(this)
                }
            },
            onDismiss = {
                datePickerVisible = false
            }
        )
    }
}

@Composable
private fun DateView(
    date: String,
    onClickDate: () -> Unit,
    onClickPrevious: () -> Unit,
    onClickNext: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
    ) {
        Spacer(Modifier.weight(1f))
        Image(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = "",
            modifier = Modifier
                .clickable { onClickPrevious() }
        )
        BaseText(
            text = date,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(145.dp)
                .padding(horizontal = 20.dp)
                .clickable { onClickDate() }
        )
        Image(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "",
            modifier = Modifier
                .clickable { onClickNext() }
        )
        Spacer(Modifier.weight(1f))
    }
}

@Composable
private fun WritingView() {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePickerModal(
    datePickerVisible: Boolean,
    onConfirm: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()
    var dateBackup: Long? by remember { mutableStateOf(0) }
    if (datePickerVisible) {
        DatePickerDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    onClick = {
                        dateBackup = datePickerState.selectedDateMillis
                        onConfirm(dateBackup)
                        onDismiss()
                    }
                ) {
                    BaseText("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis = dateBackup
                        onDismiss()
                    }
                ) {
                    BaseText("Cancel")
                }
            }
        ) {
            DatePicker(datePickerState)
        }
    }
}

@BasePreview
@Composable
private fun DatePreview() {
    DateView(
        "2024-01-20",
        {},
        {},
        {}
    )
}