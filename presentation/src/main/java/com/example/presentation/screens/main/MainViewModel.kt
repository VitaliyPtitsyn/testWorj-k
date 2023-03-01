package com.example.presentation.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.RequestPointsUseCase
import com.example.presentation.R
import com.example.presentation.base.BaseViewModel
import com.example.presentation.errorHadling.InvalidInputError
import com.example.presentation.navigation.NavigationHandler
import com.example.presentation.navigation.Screens
import com.example.presentation.uiModels.FabBtnModel
import com.example.presentation.uiModels.FabPosition
import com.example.presentation.uiModels.NavBottomAppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    navigationHandler: NavigationHandler,
    private val getPointsUseCase: RequestPointsUseCase
) : BaseViewModel(
    navigationHandler
) {

    val pointsText = MutableLiveData<String>()

    val isLoading = MutableLiveData<Boolean>()

    val displayError = MutableLiveData<java.lang.Exception?>()

    val isErrorVisible = displayError.map { it != null }


    val state = NavBottomAppState(
        fabBtnModel = FabBtnModel(
            imgResId = R.drawable.baseline_directions_car_24,
            FabPosition.CENTER,
            action = { goToRide() },
        ),
        isBarVisible = true
    )

    init {
        viewModelScope.launch {
            navigationHandler.postAppState(R.id.main_fragment, state)
        }
    }

    fun goToRide() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading.postValue(true)
                displayError.postValue(null)

                val count = pointsText.value?.toInt() ?: throw InvalidInputError()
                val points = getPointsUseCase.requestPoints(count)
                Timber.d("points $points")
                navigationHandler.sendNavigation(Screens.MainScreen.ToResultScreen(points))
            } catch (e: java.lang.Exception) {
                displayError.postValue(e)
                Timber.e(e)
            } finally {
                isLoading.postValue(false)
            }
        }
    }

}