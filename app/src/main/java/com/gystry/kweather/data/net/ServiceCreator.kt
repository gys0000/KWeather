package com.gystry.kweather.data.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object ServiceCreator {
    //kotlin变量没有默认值
    private const val BASE_URL = "http://guolin.tech/"

    private val httpClent = OkHttpClient.Builder()

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClent.build())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
    private val retorfit = builder.build()

    fun <T> create(serviceClass: Class<T>): T = retorfit.create(serviceClass)
}