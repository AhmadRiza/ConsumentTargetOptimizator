package riza.com.cto.data.net

import okhttp3.FormBody
import retrofit2.Call
import retrofit2.http.*
import riza.com.cto.model.TestModel

interface MainAPI {

    @POST("/merchant/account/updatelocation")
    fun updateUserCoordinate(
        @Body request: FormBody
    ): Call<TestModel>


}
