package kr.sdbk.core_common.custom_view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import kr.sdbk.core_common.R
import kr.sdbk.core_common.enums.TextFont
import kr.sdbk.core_common.enums.TextWeight
import kr.sdbk.core_common.util.FontProvider

class BaseEditText: AppCompatEditText {
    constructor(context: Context): super(context) { init() }
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) { init(attrs) }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) { init(attrs) }

    @SuppressLint("Recycle")
    private fun init(attrs: AttributeSet? = null) {
        attrs?.run {
            val styledAttrs = context.obtainStyledAttributes(R.styleable.BaseEditText)
            val textFont = TextFont.entries[styledAttrs.getInt(R.styleable.BaseTextView_textFont, 0)]
            val textWeight = TextWeight.entries[styledAttrs.getInt(R.styleable.BaseTextView_textWeight, 0)]

            val fontRes = FontProvider.getFontRes(textFont, textWeight)
            typeface = resources.getFont(fontRes)
        }
    }
}