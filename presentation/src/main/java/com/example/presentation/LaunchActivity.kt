package com.example.presentation


import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.core.presentation.base.screens.base.BaseMVVMActivity
import com.example.presentation.databinding.ActivityLaunchBinding
import com.example.presentation.navigation.Screens
import com.example.presentation.screens.main.MainFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LaunchActivity : BaseMVVMActivity<ActivityLaunchBinding>(R.layout.activity_launch) {

    val viewModel: MainActivityViewModel by viewModels()

    private val mainNavigation: NavController by lazy {
        Navigation.findNavController(this, R.id.main_navigation).apply {
            addOnDestinationChangedListener { controler, destination, arguments ->
                viewModel.destination.postValue(destination.id)
            }
        }
    }


    override fun attachViewModels(binding: ActivityLaunchBinding) {
        binding.vm = viewModel
        observeNavigation()
    }

    private fun observeNavigation() {
        viewModel.viewModelScope.launch(Dispatchers.Main) {
            viewModel.navigationHandler.observeNavigation().collectLatest {
                when {
                    it is Screens.NavigateBack -> {

                        mainNavigation.popBackStack()
                    }
                    it is Screens.MainScreen -> handleNaviagtion(it)
                }
            }
        }
    }

    private fun handleNaviagtion(it: Screens.MainScreen) {
        when (it) {

            is Screens.MainScreen.ToResultScreen -> {
                if (mainNavigation.currentDestination?.id == R.id.main_fragment) {
                    val desination = MainFragmentDirections.actionMainFragmentToResultFragment(
                        it.statisticsPoints
                    )
                    mainNavigation.navigate(
                        desination
                    )
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!mainNavigation.popBackStack()) {
            super.onBackPressed()
        }
    }
}