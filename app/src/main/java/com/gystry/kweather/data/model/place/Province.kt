package com.gystry.kweather.data.model.place

import com.google.gson.annotations.SerializedName
import org.litepal.crud.LitePalSupport

/**
 * @author gystry
 * @e-mail gystry@163.com
 * @GitHub https://github.com/gys0000
 * @date 2020/3/16.
 * @description:
 */
class Province(@SerializedName("name") val provinceName: String, @SerializedName("id") val provinceCode: Int) :
    LitePalSupport() {
    @Transient
    val id = 0
}