package riza.com.cto.model

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