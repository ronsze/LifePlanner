package kr.sdbk.core_common.util

import androidx.annotation.FontRes
import kr.sdbk.core_common.R
import kr.sdbk.core_common.enums.TextFont
import kr.sdbk.core_common.enums.TextWeight

object FontProvider {
    @FontRes
    fun getFontRes(textFont: TextFont, weight: TextWeight) = when (textFont) {
        TextFont.ROBOTO -> getRobotoFont(weight)
    }

    private fun getRobotoFont(weight: TextWeight) = when (weight) {
        TextWeight.REGULAR -> R.font.roboto_regular
        TextWeight.MEDIUM -> R.font.roboto_medium
        TextWeight.BOLD -> R.font.roboto_bold
    }
}