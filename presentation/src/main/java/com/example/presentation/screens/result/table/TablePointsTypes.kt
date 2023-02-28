package com.example.presentation.screens.result.table

import com.example.domain.models.StatPoint

sealed class TablePointsTypes {
    object FirstHeader : TablePointsTypes()
    data class PointsRaw(val order: Int, val point: StatPoint) : TablePointsTypes()
    data class RangeHeader(val firstPoint: StatPoint, val endPoint: StatPoint) : TablePointsTypes()
}