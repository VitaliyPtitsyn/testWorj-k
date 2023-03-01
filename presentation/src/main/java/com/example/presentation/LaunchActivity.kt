package com.example.presentation


import android.content.Intent
import android.net.Uri
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.core.presentation.base.screens.base.BaseMVVMActivity
import com.example.presentation.databinding.ActivityLaunchBinding
import com.example.presentation.navigation.Screens
import com.example.presentation.screens.main.MainFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LaunchActivity : BaseMVVMActivity<ActivityLaunchBinding>(R.layout.activity_launch) {

    val viewModel: MainActivityViewModel by viewModels()

    val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.main_navigation) as NavHostFragment
    }
    val mainNavigation by lazy {
        navHostFragment.navController.apply {
            addOnDestinationChangedListener(viewModel)
        }
    }

    override fun attachViewModels(binding: ActivityLaunchBinding) {
        binding.vm = viewModel
        observeNavigation()
    }

    override fun onResume() {
        super.onResume()
        mainNavigation.currentDestination?.let { viewModel.postDestination(it.id) }
    }

    private fun observeNavigation() {
        viewModel.viewModelScope.launch(Dispatchers.Main) {
            viewModel.navigationHandler.observeNavigation().collectLatest {
                when (it) {
                    is Screens.NavigateBack -> {
                        mainNavigation.popBackStack()
                    }
                    is Screens.ShareImage -> shareImage(it.imageUri)
                    is Screens.MainScreen -> handleNaviagtion(it)
                }
            }
        }
    }

    private fun shareImage(uri: Uri) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.setType("image/png")
        startActivity(intent)
    }

    private fun handleNaviagtion(it: Screens.MainScreen) {
        when (it) {

            is Screens.MainScreen.ToResultScreen -> {
                val desination = MainFragmentDirections.actionMainFragmentToResultFragment(it.statisticsPoints)
                mainNavigation.navigate(desination)
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return mainNavigation.navigateUp() || super.onSupportNavigateUp()
    }
}