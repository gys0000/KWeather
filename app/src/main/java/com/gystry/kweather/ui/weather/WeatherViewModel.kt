package com.gystry.kweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gystry.kweather.data.WeatherRepository
import com.gystry.kweather.data.model.weather.Weather
import com.gystry.kweather.util.toast
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * @author gystry
 * @e-mail gystry@163.com
 * @GitHub https://github.com/gys0000
 * @date 2020/3/25.
 * @description:
 */
class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    var weather = MutableLiveData<Weather>()

    var bingPicUrl = MutableLiveData<String>()

    var refreshing = MutableLiveData<Boolean>()

    var weatherInitialized = MutableLiveData<Boolean>()

    var weatherId = ""

    fun getWeather() {
        launch({
            weather.value = repository.getWeather(weatherId)
            weatherInitialized.value = true
        }, {
            toast(it.message.toString())
        })
        getBingPic(false)
    }

    fun refreshWeather() {
        refreshing.value = true
        launch({
            weather.value = repository.refreshWeather(weatherId)
            refreshing.value = false
            weatherInitialized.value = true
        }, {
            toast(it.message.toString())
            refreshing.value = false
        })
        getBingPic(true)
    }

    fun isWeatherCached() = repository.isWeatherCached()

    fun getCacheWeather() = repository.getCacheWeather()

    fun onRefresh() {
        refreshWeather()
    }

    private fun getBingPic(refresh: Boolean) {
        launch({
            bingPicUrl.value = if (refresh) repository.refreshBingPic() else repository.getBingPic()
        }, {
            toast(it.message.toString())
        })
    }

    private fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) =
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                error(e)
            }
        }
}