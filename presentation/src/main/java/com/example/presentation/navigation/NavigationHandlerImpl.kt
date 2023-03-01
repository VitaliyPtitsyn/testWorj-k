package com.example.presentation.navigation


import com.example.presentation.uiModels.NavBottomAppState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.lang.ref.WeakReference
import javax.inject.Inject

class NavigationHandlerImpl @Inject constructor(

) : NavigationHandler {
    private val mutex = Mutex()
    private val eventChannel = Channel<Screens>(Channel.BUFFERED)
    private val appSatateCash = MutableStateFlow<Map<Int, WeakReference<NavBottomAppState>>>(emptyMap())


    override suspend fun sendNavigation(screens: Screens) {
        eventChannel.send(screens)
    }

    override fun observeNavigation(): Flow<Screens> = eventChannel.receiveAsFlow()
    override suspend fun postAppState(id: Int, state: NavBottomAppState) {
        mutex.withLock {
            val map = appSatateCash.value.toMutableMap()
            map[id] = WeakReference(state)
            appSatateCash.emit(map)
        }
    }

    override fun getAppState(id: Int): Flow<NavBottomAppState> = appSatateCash
        .map { it[id]?.get() ?: NavBottomAppState() }
}