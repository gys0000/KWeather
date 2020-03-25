package com.gystry.kweather.data.net.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
//        var request = chain.request()
//        val nanoTime = System.currentTimeMillis()
//        Log.e(
//            "LoggingInterceptor",
//            "sending request  ${request.url} on ${chain.connection()} -- ${request.headers}"
//        )
//        var response = chain.proceed(request)
//        var endTime = System.currentTimeMillis()
//        var duration = endTime - nanoTime
//        val mediaType = response.body!!.contentType()
//        val content = response.body!!.string()
//        Log.e(
//            "LoggingInterceptor",
//            "Received response for ${response.request.url} in $duration ${response.headers} --  $content"
//        )

//        return response.newBuilder().body(ResponseBody.create(mediaType,content)).build()
        return chain.proceed(chain.request())
    }

}