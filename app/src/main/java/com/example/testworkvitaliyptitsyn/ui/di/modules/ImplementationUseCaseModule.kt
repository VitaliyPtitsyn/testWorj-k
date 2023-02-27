package com.example.testworkvitaliyptitsyn.ui.di.modules

import com.example.data.useCases.RequestPointsUseCaseImpl
import com.example.domain.usecases.RequestPointsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ImplementationUseCaseModule {

    @Binds
    fun bindRepository(impl: RequestPointsUseCaseImpl): RequestPointsUseCase

}