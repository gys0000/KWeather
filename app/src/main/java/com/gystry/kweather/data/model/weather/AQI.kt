package com.gystry.kweather.data.model.weather

/**
 * @author gystry
 * @e-mail gystry@163.com
 * @GitHub https://github.com/gys0000
 * @date 2020/3/16.
 * @description:
 */
class AQI {
    lateinit var city: AQICity

    inner class AQICity {
        var aqi = ""
        var pm25 = ""
    }
}