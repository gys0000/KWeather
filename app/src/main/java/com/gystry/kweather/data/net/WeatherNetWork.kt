package com.gystry.kweather.data.net

import com.gystry.kweather.data.net.api.PlaceService
import com.gystry.kweather.data.net.api.WeatherService
import com.gystry.kweather.util.log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @author gystry
 * @e-mail gystry@163.com
 * @GitHub https://github.com/gys0000
 * @date 2020/3/16.
 * @description:
 */
class WeatherNetWork {
    private val placeService = ServiceCreator.create(PlaceService::class.java)
    private val weatherService = ServiceCreator.create(WeatherService::class.java)

    suspend fun fetchProvinceList() = placeService.getProvince().await()

    suspend fun fetchCityList(provinceId: Int) = placeService.getCities(provinceId).await()

    suspend fun fetchCountyList(provinceId: Int, cityId: Int) = placeService.getCounties(provinceId, cityId).await()

    suspend fun fetchWeather(weatherId: String) = weatherService.getWeather(weatherId).await()

    suspend fun fetchBingPic() = weatherService.getBingPic().await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    var body = response.body()
                    log("3333333333-----${response.body().toString()}")
                    if (body != null) {
                        continuation.resume(body)
                    } else {
                        continuation.resumeWithException(RuntimeException("response body is null"))
                    }
                }
            })
        }
    }

    companion object {
        private var network: WeatherNetWork? = null
        fun getInstance(): WeatherNetWork {
            if (network == null) {
                synchronized(WeatherNetWork::class.java) {
                    if (network == null) {
                        network = WeatherNetWork()
                    }
                }
            }
            return network!!
        }
    }
}