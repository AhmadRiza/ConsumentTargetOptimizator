package riza.com.cto.model

import com.google.gson.annotations.Expose

/**
 * Created by riza@deliv.co.id on 5/7/20.
 */


data class Promo(
    @Expose val id: Long,
    @Expose val code: String,
    @Expose val startDate: Long,
    @Expose val endDate: Long,
    @Expose val type: String,
    @Expose val value: Int,
    @Expose val service: String,
    @Expose val description: String,
    @Expose val areas: List<AreaPromo>,
    @Expose val users: List<UserIds>
)

data class AreaPromo(
    @Expose val id: Long,
    @Expose val name: String,
    @Expose val points: String
)

data class UserIds(
    val id: Long
)

object PromoType {
    const val PERCENT = "PERCENT"
    const val PRICE = "PRICE"
}

