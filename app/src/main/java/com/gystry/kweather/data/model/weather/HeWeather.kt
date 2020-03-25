package com.coolweather.coolweatherjetpack.data.model.weather

import com.google.gson.annotations.SerializedName
import com.gystry.kweather.data.model.weather.Weather

class HeWeather {

    @SerializedName("HeWeather")
    var weather: List<Weather>? = null

}