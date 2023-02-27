package com.example.domain.usecases

import com.example.domain.models.StatisticsPoints

interface RequestPointsUseCase {

    @Throws
    suspend fun requestPoints(count: Int): StatisticsPoints
}