package com.gystry.kweather.ui

import androidx.lifecycle.ViewModel
import com.gystry.kweather.data.WeatherRepository

/**
 * @author gystry
 * @e-mail gystry@163.com
 * @GitHub https://github.com/gys0000
 * @date 2020/3/24.
 * @description:
 */
class MainViewModel(private val repository: WeatherRepository):ViewModel() {
    fun isWeatherCached()=repository.isWeatherCached()
}