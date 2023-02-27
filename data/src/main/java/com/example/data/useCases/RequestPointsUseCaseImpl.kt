package com.example.data.useCases

import com.example.data.repository.PointsRepository
import com.example.domain.models.StatisticsPoints
import com.example.domain.usecases.RequestPointsUseCase
import javax.inject.Inject

class RequestPointsUseCaseImpl @Inject constructor(
    private val pointsRepository: PointsRepository
) : RequestPointsUseCase {


    override suspend fun requestPoints(count: Int): StatisticsPoints =
        pointsRepository.getPoints(count)
}