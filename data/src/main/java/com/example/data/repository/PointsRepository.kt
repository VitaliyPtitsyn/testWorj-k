package com.example.data.repository

import com.example.domain.models.StatisticsPoints

interface PointsRepository {

    suspend fun getPoints(count: Int): StatisticsPoints
}