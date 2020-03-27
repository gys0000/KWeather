package com.gystry.kweather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gystry.kweather.data.WeatherRepository

/**
 * @author gystry
 * @e-mail gystry@163.com
 * @GitHub https://github.com/gys0000
 * @date 2020/3/24.
 * @description:
 */
class MainModelFactory(private val repository: WeatherRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}