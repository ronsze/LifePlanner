package kr.sdbk.diary

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

fun NavGraphBuilder.diaryGraph() {
    composable<Diary> {
        DiaryView()
    }
}

@Serializable data object Diary