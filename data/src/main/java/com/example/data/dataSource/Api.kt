package com.example.data.dataSource

import android.telecom.Call
import com.example.data.dataSource.module.RemotePointsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/api/test/points")
    suspend fun getPoints(
        @Query("count") count: Int
    ): RemotePointsResponse
}