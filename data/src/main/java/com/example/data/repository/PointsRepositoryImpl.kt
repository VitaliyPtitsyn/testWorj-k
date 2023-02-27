package com.example.data.repository

import com.example.data.dataSource.Api
import com.example.data.dataSource.module.RemotePoint
import com.example.data.dataSource.module.RemotePointsResponse
import com.example.domain.models.StatPoint
import com.example.domain.models.StatisticsPoints
import retrofit2.Retrofit
import javax.inject.Inject

class PointsRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
) : PointsRepository {

    private val api: Api = retrofit.create(Api::class.java)


    override suspend fun getPoints(count: Int): StatisticsPoints {
        return api.getPoints(count).toStatisticsPoints()
    }

    private fun RemotePointsResponse.toStatisticsPoints() = StatisticsPoints(
        points = this.points.map { it.toStatPoint() }.sortedBy { it.x }
    )

    private fun RemotePoint.toStatPoint() = StatPoint(
        x = x.toFloat(),
        y = y.toFloat()
    )
}