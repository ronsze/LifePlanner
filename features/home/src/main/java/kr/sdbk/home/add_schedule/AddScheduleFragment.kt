package kr.sdbk.home.add_schedule

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.sdbk.core_common.R
import kr.sdbk.core_common.compose.BaseText
import kr.sdbk.core_common.compose.fontDp
import kr.sdbk.core_common.context_view.BaseComposeFragment
import java.time.DayOfWeek

@AndroidEntryPoint
class AddScheduleFragment: BaseComposeFragment<AddScheduleViewModel>() {
    override val fragmentViewModel: AddScheduleViewModel by viewModels()

    override fun afterComposed() {}

    @Composable
    override fun Root() {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {
            var title by remember { mutableStateOf("") }
            var detail by remember { mutableStateOf("") }

            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                label = {
                    BaseText(text = "title")
                },
                value = title,
                onValueChange = {
                    title = it
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(18.dp))

            val currentDayOfWeek = DayOfWeek.MONDAY
            var selectedDayOfWeekIndex by remember { mutableStateOf(currentDayOfWeek.ordinal) }
            DayOfWeekLayer(selectedDayOfWeekIndex) {
                selectedDayOfWeekIndex = it
            }
            Spacer(modifier = Modifier.height(12.dp))


            OutlinedTextField(
                label = {
                    BaseText(text = "detail")
                },
                value = detail,
                onValueChange = {
                    detail = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(135.dp)
            )
            
            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                BaseText(
                    text = "Confirm"
                )
            }
        }
    }

    @Composable
    fun DayOfWeekLayer(
        selectedIndex: Int,
        onClick: (Int) -> Unit
    ) {
        LazyRow {
            itemsIndexed(DayOfWeek.entries.map { getDayOfWeekName(it) }) { index, item ->
                val isSelected = selectedIndex == index
                val color = if (isSelected) R.color.black else R.color.gray
                val weight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                BaseText(
                    text = item,
                    fontSize = 50.fontDp,
                    color = colorResource(id = color),
                    fontWeight = weight,
                    modifier = Modifier
                        .clickable { onClick(index) }
                )
                Spacer(modifier = Modifier.width(11.dp))
            }
        }
    }

    @Preview
    @Composable
    fun Preview() {
        Root()
    }

    private fun getDayOfWeekName(dayOfWeek: DayOfWeek) = when (dayOfWeek) {
        DayOfWeek.MONDAY -> "월요일"
        DayOfWeek.TUESDAY -> "화요일"
        DayOfWeek.WEDNESDAY -> "수요일"
        DayOfWeek.THURSDAY -> "목요일"
        DayOfWeek.FRIDAY -> "금요일"
        DayOfWeek.SATURDAY -> "토요일"
        DayOfWeek.SUNDAY -> "일요일"
    }
}