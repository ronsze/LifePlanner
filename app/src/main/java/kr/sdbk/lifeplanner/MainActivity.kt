package kr.sdbk.lifeplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import kr.sdbk.common.ui.theme.LifePlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LifePlannerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LifePlannerApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
