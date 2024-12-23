package kr.sdbk.setting

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavGraphBuilder.settingGraph() {
    composable<Setting> {
        SettingView()
    }
}

@Serializable data object Setting