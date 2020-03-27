package com.gystry.kweather.data.db

import com.gystry.kweather.data.model.place.City
import com.gystry.kweather.data.model.place.County
import com.gystry.kweather.data.model.place.Province
import org.litepal.LitePal

/**
 * @author gystry
 * @e-mail gystry@163.com
 * @GitHub https://github.com/gys0000
 * @date 2020/3/24.
 * @description:
 */
class PlaceDao {

    //从数据库中获取数据
    fun getProvinceList(): MutableList<Province> = LitePal.findAll(Province::class.java)

    fun getCityList(provinceId: Int): MutableList<City> =
        LitePal.where("provinceId = ?", provinceId.toString()).find(City::class.java)

    fun getCountyList(cityId: Int) =
        LitePal.where("cityId = ?", cityId.toString()).find(County::class.java)

    /**
     * 向数据库中存储数据
     */
    fun saveProvinceList(provinceList: List<Province>?) {
        if (provinceList != null && provinceList.isNotEmpty()) {
            LitePal.saveAll(provinceList)
        }
    }

    fun saveCityList(cityList: List<City>?) {
        if (cityList != null && cityList.isNotEmpty()) {
            LitePal.saveAll(cityList)
        }
    }

    fun saveCountyList(countyList: List<County>?) {
        if (countyList != null && countyList.isNotEmpty()) {
            LitePal.saveAll(countyList)
        }
    }

}