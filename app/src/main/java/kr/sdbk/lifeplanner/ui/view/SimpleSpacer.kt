package kr.sdbk.lifeplanner.ui.view

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SimpleSpacer(size: Dp, orientation: Orientation = Orientation.Vertical) {
    val (width, height) = when (orientation) {
        Orientation.Horizontal -> size to 0.dp
        Orientation.Vertical -> 0.dp to size
    }
    Spacer(
        modifier = Modifier.size(width, height)
    )
}