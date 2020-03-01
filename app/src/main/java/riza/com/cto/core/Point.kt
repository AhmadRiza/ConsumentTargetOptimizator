package riza.com.cto.core

import com.google.gson.annotations.SerializedName

data class Point(
    @SerializedName("lon") val x: Float,
    @SerializedName("lat") val y: Float
)