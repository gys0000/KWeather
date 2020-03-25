package com.gystry.kweather.ui.area

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coolweather.coolweatherjetpack.data.model.place.City
import com.coolweather.coolweatherjetpack.data.model.place.County
import com.gystry.kweather.data.PlaceRepository
import com.gystry.kweather.data.model.place.Province
import com.gystry.kweather.ui.area.ChooseFragment.Companion.LEVEL_CITY
import com.gystry.kweather.ui.area.ChooseFragment.Companion.LEVEL_COUNTY
import com.gystry.kweather.ui.area.ChooseFragment.Companion.LEVEL_PROVINCE
import com.gystry.kweather.util.toast
import kotlinx.coroutines.launch
import java.util.ArrayList

/**
 * @author gystry
 * @e-mail gystry@163.com
 * @GitHub https://github.com/gys0000
 * @date 2020/3/24.
 * @description:
 */
class ChooseAreaViewModel(private val repository: PlaceRepository) : ViewModel() {
    var currentLevel = MutableLiveData<Int>()

    var dataChanged = MutableLiveData<Int>()

    var isLoading = MutableLiveData<Boolean>()

    var areaSelected = MutableLiveData<Boolean>()

    var selectedProvince: Province? = null

    var selectedCity: City? = null

    var selectedCounty: County? = null

    lateinit var provinces: MutableList<Province>

    lateinit var cities: MutableList<City>

    lateinit var counties: MutableList<County>

    val dataList = ArrayList<String>()

    fun getProvinces() {
        currentLevel.value = LEVEL_PROVINCE
        launch {
            provinces = repository.getProvinceList()
            //MutableList 的map可以处理并获取新的list
            dataList.addAll(provinces.map { it.provinceName })
        }
    }

    private fun getCities() = selectedProvince?.let {
        currentLevel.value = LEVEL_CITY
        launch {
            cities = repository.getCityList(it.provinceCode)
            dataList.addAll(cities.map { it.cityName })
        }
    }

    private fun getCounties() = selectedCity?.let {
        currentLevel.value = LEVEL_COUNTY
        launch {
            counties = repository.getCountyList(it.provinceId, it.cityCode)
            dataList.addAll(counties.map { it.countyName })
        }
    }

    fun onListViewItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        when {
            currentLevel.value == LEVEL_PROVINCE -> {
                selectedProvince = provinces[position]
                getCities()
            }
            currentLevel.value == LEVEL_CITY -> {
                selectedCity = cities[position]
                getCounties()
            }
            currentLevel.value == LEVEL_COUNTY -> {
                selectedCounty = counties[position]
                areaSelected.value = true
            }
        }
    }

    fun onBack() {
        if (currentLevel.value == LEVEL_COUNTY) {
            getCities()
        } else if (currentLevel.value == LEVEL_CITY) {
            getProvinces()
        }
    }

    private fun launch(block: suspend () -> Unit) = viewModelScope.launch {
        try {
            isLoading.value = true
            dataList.clear()
            block()
            dataChanged.value = dataChanged.value?.plus(1)
            isLoading.value = false
        } catch (t: Throwable) {
            t.printStackTrace()
            toast(t.message.toString())
            dataChanged.value = dataChanged.value?.plus(1)
            isLoading.value = false
        }
    }

}