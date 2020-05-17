package riza.com.cto.data.api

import kotlinx.coroutines.Deferred
import okhttp3.FormBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import riza.com.cto.model.AreaPromo
import riza.com.cto.model.Promo
import riza.com.cto.model.PromoRequest
import riza.com.cto.model.TestModel
import riza.com.cto.support.base.BaseResponse

interface MainAPI {

    @POST("/merchant/account/updatelocation")
    fun updateUserCoordinate(
        @Body request: FormBody
    ): Call<TestModel>


//    @POST("/merchant/auth/login")
//    fun loginAsync(
//        @Body request: AuthRequest
//    ): Deferred<Response<BaseResponse<AuthResponse>>>


    @GET("promo/all")
    fun getAllPromoAsync(): Deferred<Response<BaseResponse<List<Promo>>>>

    @POST("promo/add")
    fun addPromoAsync(@Body body: PromoRequest): Deferred<Response<BaseResponse<Any>>>

    @GET("area/all")
    fun getAllAreaAsync(): Deferred<Response<BaseResponse<List<AreaPromo>>>>




}
