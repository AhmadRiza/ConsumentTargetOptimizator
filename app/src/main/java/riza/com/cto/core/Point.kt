package riza.com.cto.core

import com.google.gson.annotations.SerializedName

data class Point(
    @SerializedName("lon") val x: Double,
    @SerializedName("lat") val y: Double
)