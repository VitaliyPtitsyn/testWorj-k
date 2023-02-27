package com.example.testworkvitaliyptitsyn.ui.di.modules

import com.example.data.repository.PointsRepository
import com.example.data.repository.PointsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ImplementationRepositoryModule {

    @Binds
    fun bindRepository(impl: PointsRepositoryImpl): PointsRepository

}