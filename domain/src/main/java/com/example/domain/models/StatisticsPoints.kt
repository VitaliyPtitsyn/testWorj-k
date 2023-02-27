package com.example.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StatisticsPoints(
    val points: List<StatPoint>
):Parcelable