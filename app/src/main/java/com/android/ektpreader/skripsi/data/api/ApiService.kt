package com.android.ektpreader.skripsi.data.api

import com.android.ektpreader.skripsi.BuildConfig.BASE_URL
import com.android.ektpreader.skripsi.BuildConfig.KEY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {
//    private const val reserve = "http://192.168.42.137/rest_api/public/"
    private val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().
    setLevel(HttpLoggingInterceptor.Level.BODY))
    .addInterceptor {
        val ori = it.request()
        val requestBuilder = ori.newBuilder().header("Authorization", "Bearer $KEY")
        val req = requestBuilder.build()
        it.proceed(req)
    }
    .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}