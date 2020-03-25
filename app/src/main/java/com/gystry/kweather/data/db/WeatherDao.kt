package com.gystry.kweather.data.db

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.gystry.kweather.MyApplication
import com.gystry.kweather.data.model.weather.Weather

/**
 * @author gystry
 * @e-mail gystry@163.com
 * @GitHub https://github.com/gys0000
 * @date 2020/3/24.
 * @description:
 */
class WeatherDao {

    /**
     * 存储数据
     * @param weather Weather?
     */
    fun cacheWeatherInfo(weather: Weather?) {
        if (weather == null) return
        PreferenceManager.getDefaultSharedPreferences(MyApplication.context).edit {
            var weatherInfo = Gson().toJson(weather)
            putString("weather", weatherInfo)
        }
    }

    /**
     * 获取数据
     * @return Weather?
     */
    fun getCacheWeatherInfo(): Weather? {
        val weatherInfo = PreferenceManager.getDefaultSharedPreferences(MyApplication.context)
            .getString("weather", null)
        if (weatherInfo != null) {
            return Gson().fromJson(weatherInfo, Weather::class.java)
        }
        return null
    }

    fun cacheBingPic(bingPic: String?) {
        if (bingPic == null) return
        PreferenceManager.getDefaultSharedPreferences(MyApplication.context).edit {
            putString("bing_pic", bingPic)
        }
    }

    fun getCachedBingPic(): String? = PreferenceManager.getDefaultSharedPreferences(MyApplication.context).getString("bing_pic", null)

    private fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
        val editor = edit()
        action(editor)
        editor.apply()
    }
}