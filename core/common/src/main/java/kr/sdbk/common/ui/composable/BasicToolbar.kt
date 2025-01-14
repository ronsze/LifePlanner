package kr.sdbk.common.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BasicToolbar(
    title: @Composable () -> Unit = {},
    frontComposable: @Composable () -> Unit = {},
    rearComposable: @Composable () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .padding(horizontal = 5.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(24.dp)
        ) {
            frontComposable()
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(horizontal = 10.dp)
        ) {
            title()
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(24.dp)
        ) {
            rearComposable()
        }

    }
}

@BasePreview
@Composable
private fun BasicToolbarPreview() {
    BasicToolbar(
        title = BasicToolbarDefaults.defaultTitleComposable(
            title = "Android meeting"
        ),
        frontComposable = BasicToolbarDefaults.defaultIconComposable(
            onClick = {}
        ),
        rearComposable = BasicToolbarDefaults.defaultIconComposable(
            image = Icons.Filled.Add,
            onClick = {}
        )
    )
}

object BasicToolbarDefaults {
    fun defaultTitleComposable(
        title: String,
        fontSize: TextUnit = 16.sp
    ): @Composable () -> Unit = {
        BaseText(
            text = title,
            maxLines = 1,
            fontSize = fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }

    fun defaultIconComposable(
        image: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
        enabled: Boolean = true,
        size: Dp = 21.dp,
        onClick: () -> Unit
    ): @Composable () -> Unit = {
        val color = if (enabled) Color.Black else Color.LightGray
        Image(
            imageVector = image,
            contentDescription = "",
            colorFilter = ColorFilter.tint(color),
            modifier = Modifier
                .size(size)
                .clickable {
                    if (enabled) onClick()
                }
        )
    }
}