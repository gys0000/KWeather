package com.gystry.kweather.util

import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.coolweather.coolweatherjetpack.data.model.weather.Forecast
import com.gystry.kweather.R
import com.gystry.kweather.data.model.weather.Weather

/**
 * @author gystry
 * @e-mail gystry@163.com
 * @GitHub https://github.com/gys0000
 * @date 2020/3/24.
 * @description:
 */

@BindingAdapter("bind:loadBingPic")
fun ImageView.loadBingPic(url: String?) {
    if (url != null) {
        Glide.with(context).load(url).into(this)
    }
}

//@BindingAdapter("bind:colorSchemeResourse")
//fun SwipeRefreshLayout.colorSchemeResourse(resId: Int) {
//    setColorSchemeColors(resId)
//}


//fun LinearLayout.showForecast(weather: Weather?) = weather?.let {
//    removeAllViews()
//    for (forecast in it.forecastList) {
//        LayoutInflater.from(context).inflate(R.layout.forecast_item, this, false)
//        DataBindingUtil.bind<ForecastItemBinding>(view)?.forecast = forecast
//        addView(view)
//    }
//}