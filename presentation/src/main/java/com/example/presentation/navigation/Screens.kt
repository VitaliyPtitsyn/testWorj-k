package com.example.presentation.navigation

import com.example.domain.models.StatisticsPoints

sealed class Screens() {

    class NavigateBack():Screens()
    sealed class MainScreen : Screens() {
        class ToResultScreen(
            val statisticsPoints: StatisticsPoints
        ) : MainScreen()
    }

    sealed class ResultScreen : Screens() {

    }
}
