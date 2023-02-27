package com.example.presentation.screens.result

import com.example.presentation.base.BaseViewModel
import com.example.presentation.navigation.NavigationHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    navigationHandler: NavigationHandler,
) : BaseViewModel(
    navigationHandler
) {
}