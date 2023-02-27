package com.example.presentation

import androidx.lifecycle.MutableLiveData
import com.example.presentation.base.BaseViewModel
import com.example.presentation.navigation.NavigationHandler
import com.example.presentation.uiModels.NavBottomAppState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    navigationHandler: NavigationHandler
) : BaseViewModel(
    navigationHandler = navigationHandler
) {

    val lvNavBottomApp = MutableLiveData<NavBottomAppState>()

    val destination = MutableLiveData<Int>()

    fun fubAction() {

    }

}