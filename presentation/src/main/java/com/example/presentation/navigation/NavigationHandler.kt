package com.example.presentation.navigation

import kotlinx.coroutines.flow.Flow


interface NavigationHandler {

    suspend fun sendNavigation(screens: Screens)

    fun observeNavigation(): Flow<Screens>
}