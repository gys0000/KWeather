package com.gystry.kweather.data.net.api

import com.gystry.kweather.data.model.place.City
import com.gystry.kweather.data.model.place.County
import com.gystry.kweather.data.model.place.Province
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author gystry
 * @e-mail gystry@163.com
 * @GitHub https://github.com/gys0000
 * @date 2020/3/16.
 * @description:
 */
interface PlaceService {
    @GET("api/china")
    fun getProvince(): Call<MutableList<Province>>

    @GET("api/china/{provinceId}")
    fun getCities(@Path("provinceId") provinceId: Int): Call<MutableList<City>>

    @GET("api/china/{provinceId}/{cityId}")
    fun getCounties(@Path("provinceId") provinceId: Int, @Path("cityId") cityId: Int): Call<MutableList<County>>
}