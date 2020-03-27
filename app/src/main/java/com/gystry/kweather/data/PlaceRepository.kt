package com.gystry.kweather.data

import com.gystry.kweather.data.db.PlaceDao
import com.gystry.kweather.data.net.WeatherNetWork
import com.gystry.kweather.util.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author gystry
 * @e-mail gystry@163.com
 * @GitHub https://github.com/gys0000
 * @date 2020/3/24.
 * @description: 带参单例不能使用object
 */
class PlaceRepository private constructor(
    private val placeDao: PlaceDao,
    private val network: WeatherNetWork
) {

    suspend fun getProvinceList() = withContext(Dispatchers.IO) {
        log("22222222-----${network.fetchProvinceList().toString()}")
        var provinceList = placeDao.getProvinceList()
        log("44444444-----${network.fetchProvinceList().toString()}")
        if (provinceList.isEmpty()) {
            provinceList = network.fetchProvinceList()
            placeDao.saveProvinceList(provinceList)
        }
        provinceList
    }

    suspend fun getCityList(provinceId: Int) = withContext(Dispatchers.IO) {
        var cityList = placeDao.getCityList(provinceId)
        if (cityList.isEmpty()) {
            cityList = network.fetchCityList(provinceId)
            //it关键字指的是list里边的每一项
            cityList.forEach { it.provinceId = provinceId }
            placeDao.saveCityList(cityList)
        }
        cityList
    }

    suspend fun getCountyList(provinceId: Int, cityId: Int) = withContext(Dispatchers.IO) {
        var list = placeDao.getCountyList(cityId)
        if (list.isEmpty()) {
            list = network.fetchCountyList(provinceId, cityId)
            list.forEach { it.cityId = cityId }
            placeDao.saveCountyList(list)
        }
        list
    }

    companion object {
        private var instance: PlaceRepository? = null
        fun getInstance(placeDao: PlaceDao, network: WeatherNetWork): PlaceRepository {
            if (instance == null) {
                synchronized(PlaceRepository::class.java) {
                    if (instance == null) {
                        instance = PlaceRepository(placeDao, network)
                    }
                }
            }
            return instance!!
        }
    }

}