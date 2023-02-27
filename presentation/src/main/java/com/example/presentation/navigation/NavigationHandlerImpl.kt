package com.example.presentation.navigation


import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class NavigationHandlerImpl @Inject constructor(

) : NavigationHandler {
    private val eventChannel = Channel<Screens>(Channel.BUFFERED)


    override suspend fun sendNavigation(screens: Screens) {
        eventChannel.send(screens)

    }

    override fun observeNavigation(): Flow<Screens> = eventChannel.receiveAsFlow()
}