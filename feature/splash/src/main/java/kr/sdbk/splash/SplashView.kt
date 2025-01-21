package kr.sdbk.splash

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kr.sdbk.common.ui.composable.BasePreview
import kotlin.system.exitProcess

@Composable
fun SplashView(
    navigateToHome: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(kr.sdbk.common.R.drawable.loading),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(horizontal = 25.dp)
                .align(Alignment.Center)
        )
    }

    RequestNotificationPermission(
        navigateToHome = navigateToHome
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun RequestNotificationPermission(
    navigateToHome: () -> Unit
) {
    val context = LocalContext.current
    if (Build.VERSION.SDK_INT >= 26) {
        val name = "schedule notification"
        val description = ""
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel("sdbk-lifeplanner", name, importance)
        mChannel.description = description
        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
    }

    if (Build.VERSION.SDK_INT >= 33) {
        val permission = Manifest.permission.POST_NOTIFICATIONS
        val permissionState = rememberPermissionState(permission)
        if (permissionState.status.isGranted) {
            navigateToHome()
        } else {
            if (permissionState.status.shouldShowRationale) {
                Toast.makeText(context, "Please allow permission", Toast.LENGTH_SHORT).show()
                exitProcess(0)
            } else {
                LaunchedEffect(Unit) {
                    permissionState.launchPermissionRequest()
                }
            }
        }
    }
}

@BasePreview
@Composable
private fun Preview() {
    SplashView {  }
}