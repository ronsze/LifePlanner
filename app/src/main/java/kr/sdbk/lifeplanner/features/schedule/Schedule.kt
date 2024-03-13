package kr.sdbk.lifeplanner.features.schedule

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Schedule(viewModel: ScheduleViewModel = viewModel()) {
    Text(text = "Schedule")
}