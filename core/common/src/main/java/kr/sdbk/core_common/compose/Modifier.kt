package kr.sdbk.core_common.compose

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.dashedBorder(
    width: Float,
    size: Float,
    cap: Float,
    color: Color,
    radius: Dp = 0.dp
) = drawBehind {
    val stroke = Stroke(
        width = width,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(size, cap))
    )

    drawRoundRect(
        color = color,
        style = stroke,
        cornerRadius = CornerRadius(radius.toPx())
    )
}