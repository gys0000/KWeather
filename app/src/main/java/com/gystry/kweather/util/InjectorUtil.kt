package com.gystry.kweather.util

import com.gystry.kweather.data.PlaceRepository
import com.gystry.kweather.data.WeatherRepository
import com.gystry.kweather.data.db.CoolWeatherDatabase
import com.gystry.kweather.data.db.WeatherDao
import com.gystry.kweather.data.net.WeatherNetWork
import com.gystry.kweather.ui.MainModelFactory
import com.gystry.kweather.ui.area.ChooseAreaModelFactory
import com.gystry.kweather.ui.weather.WeatherModelFactory

/**
 * @author gystry
 * @e-mail gystry@163.com
 * @GitHub https://github.com/gys0000
 * @date 2020/3/24.
 * @description:
 */
object InjectorUtil {
    private fun getPlaceRepository() =
        PlaceRepository.getInstance(CoolWeatherDatabase.getPlaceDao(), WeatherNetWork.getInstance())

    private fun getWeatherRepository() = WeatherRepository.getInstances(
        CoolWeatherDatabase.getWeatherDao(),
        WeatherNetWork.getInstance()
    )

    fun getMainModelFactory() = MainModelFactory(getWeatherRepository())

    fun getChooseAreaModelFactory() = ChooseAreaModelFactory(getPlaceRepository())

    fun getWeatherModelFactory() = WeatherModelFactory(getWeatherRepository())

}