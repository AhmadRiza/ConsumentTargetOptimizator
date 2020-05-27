package riza.com.cto.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

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

@Parcelize
data class UserIds(
    val id: Long,
    val name: String
) : Parcelable
