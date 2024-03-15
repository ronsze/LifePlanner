package kr.sdbk.lifeplanner.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.sdbk.domain.model.DayOfWeek

@Composable
fun TabLayout(
    items: List<String>,
    modifier: Modifier,
    initialTabIndex: Int = 0,
) {
    Row(
        modifier = modifier
    ) {
        val selectedIndex: MutableState<Int> = remember { mutableStateOf(initialTabIndex) }
        items.forEachIndexed { i, v ->
            TabRow(
                index = i,
                text = v,
                selectedIndex = selectedIndex,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )
        }
    }
}

@Composable
private fun TabRow(
    index: Int,
    text: String,
    selectedIndex: MutableState<Int>,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .clickable { selectedIndex.value = index }
    ) {
        val isSelected = index == selectedIndex.value
        val color = if (isSelected) Color.Cyan else Color.Black
        TabText(
            text = text,
            color = color,
            modifier = Modifier.align(Alignment.Center)
        )
        AnimatedVisibility(
            visible = isSelected,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            TabIndicator(modifier = Modifier)
        }
    }
}

@Composable
private fun TabText(
    text: String,
    color: Color,
    modifier: Modifier
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = text,
            color = color,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
private fun TabIndicator(
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(2.dp)
            .clip(RectangleShape)
            .background(Color.Cyan)
    )
}

@Preview
@Composable
fun TabRowPreview() {
    Row {
        TabRow(0, "MON", mutableStateOf(0), Modifier.height(55.dp))
    }
}