package com.example.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.navigation.ActivityNavigator
import com.example.presentation.base.BaseViewModel
import com.example.presentation.navigation.NavigationHandler
import com.example.presentation.navigation.Screens
import com.example.presentation.uiModels.FabBtnModel
import com.example.presentation.uiModels.FabPosition
import com.example.presentation.uiModels.NavBottomAppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    navigationHandler: NavigationHandler
) : BaseViewModel(
    navigationHandler = navigationHandler
) {

    val destination = MutableStateFlow<Int>(-1)

    val lvNavBottomApp = destination.flatMapLatest { id ->
        navigationHandler.getAppState(id)
    }.asLiveData(viewModelScope.coroutineContext)


    fun fubAction() = viewModelScope.launch {
        lvNavBottomApp.value?.fabBtnModel?.action?.invoke()
    }

    fun postDestination(id:Int)  = viewModelScope.launch {
        destination.emit(id)
    }

}