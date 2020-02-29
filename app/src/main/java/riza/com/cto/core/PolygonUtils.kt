package riza.com.cto.core

/**
 * Created by riza@deliv.co.id on 2/29/20.
 */


object PolygonUtils {


    fun calculateCentroid(data: List<Point>): Point {

        var latSum: Double = 0.0
        var lonSum: Double = 0.0

        data.forEach {
            latSum += it.y
            lonSum += it.x
        }

        return Point(
            (lonSum/data.size),
            (latSum/data.size)
        )

    }


}