package com.gystry.kweather.data.model.weather

import com.coolweather.coolweatherjetpack.data.model.weather.Forecast
import com.coolweather.coolweatherjetpack.data.model.weather.Now
import com.coolweather.coolweatherjetpack.data.model.weather.Suggestion
import com.google.gson.annotations.SerializedName

class Weather {
    var status = ""
    lateinit var basic: Basic
    lateinit var aqi: AQI
    lateinit var now: Now
    lateinit var suggestion: Suggestion
    @SerializedName("daily_forecast")
    lateinit var forecastList: List<Forecast>
}