package riza.com.cto.data.api

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import id.co.deliv.kios.repository.api.InterceptorFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object APIServiceFactory {

    private val okHttp = OkHttpClient
        .Builder()
        .addInterceptor(InterceptorFactory.loggingInterceptor())


    private val builder = Retrofit.Builder()
        .addConverterFactory(
            GsonConverterFactory.create(
                Gson()
            )
        )
        .addCallAdapterFactory(CoroutineCallAdapterFactory())


    private fun <S> createService(
        serviceClass: Class<S>, authToken: String?
    ): S {

        if (!authToken.isNullOrBlank()) {
            val interceptor = InterceptorFactory.authInterceptor(authToken)
            if (!okHttp.interceptors().contains(interceptor)) {
                okHttp.addInterceptor(interceptor)
            }
        }

        builder.client(okHttp.build())
        return builder.build().create(serviceClass)
    }

    fun <S> createMain(serviceClass: Class<S>): S {
        builder.baseUrl(getBaseURL())
        return createService(serviceClass, null)
    }

    private fun getBaseURL() = "http://localhost:8080/"

}