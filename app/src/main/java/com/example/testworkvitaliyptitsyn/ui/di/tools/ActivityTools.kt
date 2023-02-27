package com.example.testworkvitaliyptitsyn.ui.di.tools

import com.example.presentation.errorHadling.ErrorHandler
import com.example.presentation.errorHadling.ErrorHandlerImpl
import com.example.presentation.navigation.NavigationHandler
import com.example.presentation.navigation.NavigationHandlerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface ActivityTools {

    @Binds
    @ActivityRetainedScoped
    fun bindErrorHandler(impl: ErrorHandlerImpl): ErrorHandler

    @Binds
    @ActivityRetainedScoped
    fun bindNavigationHandler(impl: NavigationHandlerImpl): NavigationHandler
}