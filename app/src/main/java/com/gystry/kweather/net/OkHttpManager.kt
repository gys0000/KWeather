package com.gystry.kweather.net

import android.util.Log
import com.gystry.kweather.net.interceptor.LoggingInterceptor
import okhttp3.*
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap

class OkHttpManager {
    private val baseUrl = "https://wanandroid.com/wxarticle/chapters/json"
    //    private val baseUrl = "https://www.wanandroid.com/lg/uncollect_originId/2333/json"
    private val okHttpClient: OkHttpClient =
        OkHttpClient.Builder().addInterceptor(LoggingInterceptor()).build()//初始化

    private fun getNewCall(): Call {
        return okHttpClient.newCall(
            Request.Builder().url(baseUrl)
                .get().build()
        )
    }

    private fun postNewCall(map: HashMap<String, String>): Call {
        var body = FormBody.Builder().apply {
            for (key in map.keys) {
                map[key]?.let { this.add(key, it) }
            }
        }.build()
        return okHttpClient.newCall(Request.Builder().url(baseUrl).post(body).build())
    }

    fun requestToGet() {
        getNewCall().enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("OkHttpManager", "onFailure$e")

            }

            override fun onResponse(call: Call, response: Response) {
                Log.e("OkHttpManager", "onResponse${response.body?.string()}")
            }

        })
    }

    fun requestToExecuteGet() {
        Thread {
            val response = getNewCall().execute()
            Log.e(
                "OkHttpManager-",
                "requestToExecute-onResponse${response.message}--" +
                        "--${response.body?.string()}"
            )
        }.start()
    }

    fun requestToPost(map: HashMap<String, String>) {
        postNewCall(map).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("OkHttpManager", "requestToPost-onFailure$e")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.e("OkHttpManager", "requestToPost-onResponse${response.body?.string()}")
            }
        })
    }

    companion object {
        val getInstance: OkHttpManager = OkHttpManager()
    }
}