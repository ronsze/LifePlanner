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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
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
        frontComposable()
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            title()
        }
        rearComposable()
    }
}

@Preview
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
            fontSize = fontSize,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
        )
    }

    fun defaultIconComposable(
        image: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
        size: Dp = 21.dp,
        onClick: () -> Unit
    ): @Composable () -> Unit = {
        Image(
            imageVector = image,
            contentDescription = "",
            modifier = Modifier
                .size(size)
                .clickable { onClick() }
        )
    }
}