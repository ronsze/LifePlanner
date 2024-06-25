package kr.sdbk.core_common.custom_view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class BaseImageView: AppCompatImageView {
    constructor(context: Context): super(context) { init() }
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) { init(attrs) }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) { init(attrs) }

    private fun init(attrs: AttributeSet? = null) {
        attrs?.run {

        }
    }
}