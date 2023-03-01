package com.example.presentation.navigation

import android.net.Uri
import com.example.domain.models.StatisticsPoints

sealed class Screens() {

    class NavigateBack() : Screens()
    class ShareImage(val imageUri: Uri) : Screens()
    sealed class MainScreen : Screens() {
        class ToResultScreen(
            val statisticsPoints: StatisticsPoints
        ) : MainScreen()
    }

    sealed class ResultScreen : Screens() {

    }
}
