package no.nordli.brynje.weather.core.network.presentation

import android.util.Log
import com.google.gson.GsonBuilder
import no.nordli.brynje.weather.core.network.data.Apis
import no.nordli.brynje.weather.core.network.domain.WeatherApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WeatherClient {
    private val clientBuilder = OkHttpClient.Builder()
        .connectTimeout(45, TimeUnit.SECONDS)
        .addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val builder =
                chain.request().newBuilder().addHeader( "Accept","application/json").addHeader("User-Agent", "Android")
            chain.proceed(builder.build())
        })
        .readTimeout(45, TimeUnit.SECONDS)
        .writeTimeout(45, TimeUnit.SECONDS)
    private val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .create()
    val api: WeatherApi = Retrofit.Builder().baseUrl(Apis.baseUrl).client(clientBuilder.build())
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
        .create(WeatherApi::class.java)
}