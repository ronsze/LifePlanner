package kr.sdbk.lifeplanner.base

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import kr.sdbk.lifeplanner.R
import kr.sdbk.lifeplanner.ui.theme.LifePlannerTheme

abstract class BaseActivity<out T: BaseViewModel>: AppCompatActivity() {
    abstract val viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InitView()
        }
    }

    @Composable
    abstract fun InitView()

    protected fun startActivitySlide(intent: Intent) {
        startActivity(intent)
        if (Build.VERSION.SDK_INT >= 34) overrideActivityTransition(Activity.OVERRIDE_TRANSITION_OPEN, R.anim.slide_in_right, R.anim.slide_out_left)
        else overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}