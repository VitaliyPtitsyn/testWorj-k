package com.example.presentation.navigation

import com.example.presentation.uiModels.NavBottomAppState
import kotlinx.coroutines.flow.Flow


interface NavigationHandler {

    suspend fun sendNavigation(screens: Screens)

    fun observeNavigation(): Flow<Screens>

   suspend fun postAppState(id: Int, state: NavBottomAppState)
    fun getAppState(id: Int): Flow<NavBottomAppState>
}