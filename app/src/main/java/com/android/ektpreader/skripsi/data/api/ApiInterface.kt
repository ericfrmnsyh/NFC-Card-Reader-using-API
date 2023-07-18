package com.android.ektpreader.skripsi.data.api

import com.android.ektpreader.skripsi.data.*
import com.android.ektpreader.skripsi.data.response.DataItem
import com.android.ektpreader.skripsi.data.response.LogResponse
import com.android.ektpreader.skripsi.data.response.NewLogResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("ktp/show/{tag}")
    fun getDataItem(
        @Path("tag") tag: String
    ): Call<DataItem>

    @POST("log/create")
    fun sendLog(
        @Body dataLog: LogModel
    ): Call<LogResponse>

    @GET("log")
    fun getAllLog(): Call<NewLogResponse>

    @GET("log/show/{nik}")
    fun getLogItem(
        @Path("nik") nik: String
    ): Call<NewLogResponse>

    @POST("req/create")
    fun sendReq(
        @Body addData: AddModel
    ): Call<LogResponse>
}