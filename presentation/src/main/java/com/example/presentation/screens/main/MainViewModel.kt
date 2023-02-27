package com.example.presentation.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.RequestPointsUseCase
import com.example.presentation.base.BaseViewModel
import com.example.presentation.errorHadling.InvalidInputError
import com.example.presentation.navigation.NavigationHandler
import com.example.presentation.navigation.Screens
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

    val displayError = MutableLiveData<String>()

    val isErrorVisible = displayError.map { it.isNotBlank() }

    fun goToRide() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading.postValue(true)
                displayError.postValue("")

                val count = pointsText.value?.toInt() ?: throw InvalidInputError()
                val points = getPointsUseCase.requestPoints(count)
                Timber.d("points $points")
                navigationHandler.sendNavigation(Screens.MainScreen.ToResultScreen(points))
            } catch (e: java.lang.Exception) {
                val text = when (e) {
                    is InvalidInputError -> {
                        "Invalid imput"
                    }
                    else -> {
                        "smth whne wrong"
                    }
                }
                displayError.postValue(text)
                Timber.e(e)
            } finally {
                isLoading.postValue(false)
            }
        }
    }

}