package com.gystry.kweather.util

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import com.gystry.kweather.MyApplication

/**
 * @author gystry
 * @e-mail gystry@163.com
 * @GitHub https://github.com/gys0000
 * @date 2020/3/24.
 * @description:
 */

@SuppressLint("ShowToast")
fun toast(msg: String) {
    Toast.makeText(MyApplication.context, msg, Toast.LENGTH_LONG)
}

fun Any.log(msg: String,tag:String=this::class.java.name) {
    Log.e(tag, msg)
}