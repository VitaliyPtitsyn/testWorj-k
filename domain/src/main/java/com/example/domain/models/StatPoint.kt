package com.example.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StatPoint(val x: Float, val y: Float) : Parcelable