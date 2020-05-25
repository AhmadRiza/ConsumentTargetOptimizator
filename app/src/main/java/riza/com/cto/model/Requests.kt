package riza.com.cto.model

import riza.com.cto.core.Point

/**
 * Created by riza@deliv.co.id on 5/18/20.
 */

data class PromoRequest(
    var code: String = "",
    var startDate: Long = 0L,
    var endDate: Long = 0L,
    var type: String = PromoType.PERCENT,
    var varue: Int = 0,
    var service: String = "",
    var description: String = "",
    val areaIds: ArrayList<Long> = arrayListOf()
)

data class AddAreaRequest(
    val name: String,
    val points: List<Point>
)