package com.gystry.kweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import org.litepal.LitePal

/**
 * @author gystry
 * @e-mail gystry@163.com
 * @GitHub https://github.com/gys0000
 * @date 2020/3/24.
 * @description:
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LitePal.initialize(this)
        context = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}