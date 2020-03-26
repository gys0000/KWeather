package com.gystry.kweather.data

import com.gystry.kweather.data.db.WeatherDao
import com.gystry.kweather.data.model.weather.Weather
import com.gystry.kweather.data.net.WeatherNetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author gystry
 * @e-mail gystry@163.com
 * @GitHub https://github.com/gys0000
 * @date 2020/3/24.
 * @description:
 */
class WeatherRepository private constructor(
    private val weatherDao: WeatherDao,
    private val network: WeatherNetWork
) {

    suspend fun getWeather(weatherId: String): Weather {
        var cacheWeatherInfo = weatherDao.getCacheWeatherInfo()
        if (cacheWeatherInfo == null) cacheWeatherInfo = requestWeather(weatherId)
        return cacheWeatherInfo
    }

    suspend fun refreshWeather(weatherId: String) = requestWeather(weatherId)

    suspend fun getBingPic(): String {
        var url = weatherDao.getCachedBingPic()
        if (url == null) url = requestBingPic()
        return url
    }
    suspend fun refreshBingPic() = requestBingPic()
    fun isWeatherCached() = weatherDao.getCacheWeatherInfo() != null

    fun getCacheWeather() = weatherDao.getCacheWeatherInfo()!!

    private suspend fun requestWeather(weatherId: String) = withContext(Dispatchers.IO) {
        var heWeather = network.fetchWeather(weatherId)
        var weather = heWeather.weather!![0]
        weatherDao.cacheWeatherInfo(weather)
        weather
    }

    private suspend fun requestBingPic() = withContext(Dispatchers.IO) {
        var url = network.fetchBingPic()
        weatherDao.cacheBingPic(url)
        url
    }

    companion object {
        private lateinit var instances: WeatherRepository
        fun getInstances(weatherDao: WeatherDao, network: WeatherNetWork): WeatherRepository {
            if (!::instances.isInitialized) {
                synchronized(WeatherRepository::class.java) {
                    if (!this::instances.isInitialized) {
                        instances = WeatherRepository(weatherDao, network)
                    }
                }
            }
            return instances
        }
    }
}