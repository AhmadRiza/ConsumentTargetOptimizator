package riza.com.cto.data.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val client =
        OkHttpClient.Builder().addInterceptor(InterceptorFactory.loggingInterceptor())
            .addInterceptor(InterceptorFactory.jsonTypeInterceptor())

    private val retrofit = Retrofit.Builder()
        .baseUrl("test")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client.build())
        .build()

    fun mainApi() = retrofit.create(
        MainAPI::class.java)

}