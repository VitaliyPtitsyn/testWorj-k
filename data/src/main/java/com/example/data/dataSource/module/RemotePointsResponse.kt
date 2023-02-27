package com.example.data.dataSource.module

import javax.inject.Named

data class RemotePointsResponse(
    @Named("points")
    val points: List<RemotePoint>

)

data class RemotePoint(
    @Named("x")
    val x: Float,
    @Named("y")
    val y: Float
)
