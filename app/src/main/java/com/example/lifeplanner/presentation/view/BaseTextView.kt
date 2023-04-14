package com.example.lifeplanner.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.lifeplanner.R
import java.util.*

class BaseTextView: AppCompatTextView {
    enum class FontEnum { Regular, Medium, Bold }

    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) { initFont(attrs); }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr) { initFont(attrs); }

    @SuppressLint("Recycle")
    fun initFont(attrs: AttributeSet?) {
        val attributeArray = context.obtainStyledAttributes(attrs, R.styleable.BaseTextView)
        val font = FontEnum.values()[(attributeArray.getString(R.styleable.BaseTextView_textFont) ?: "0").toInt()].name

        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-$font.ttf")
    }
}