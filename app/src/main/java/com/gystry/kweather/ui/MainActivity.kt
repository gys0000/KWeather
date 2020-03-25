package com.gystry.kweather.ui

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.gystry.kweather.R
import com.gystry.kweather.data.net.OkHttpManager
import com.gystry.kweather.data.net.WeatherNetWork
import com.gystry.kweather.util.InjectorUtil
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var viewModel = ViewModelProviders.of(this, InjectorUtil.getWeatherModelFactory())
            .get(MainViewModel::class.java)
        if (viewModel.isWeatherCached()) {

        } else {
//            supportFragmentManager.beginTransaction().replace(R.id.container,Choos)
        }


//        tv_content.setOnClickListener {
//            OkHttpManager.getInstance.requestToGet()
////            val map: HashMap<String, String> = HashMap()
////            map.put("id","2334")
////            OkHttpManager.getInstance.requestToPost(map)
//        }
//
//        Log.e("MainActivity", "" + Thread.currentThread().name)
//        GlobalScope.launch {
//            Log.e("MainActivity", "" + Thread.currentThread().name)
//        }
//        //相当于线程
//        GlobalScope.launch(Dispatchers.Main) {
//            Log.e("MainActivity", "000-1111")
//            val bitmap = getBitmap()
//            Log.e("MainActivity", "000-333333")
//            iv_img.setImageDrawable(bitmap)
//            Log.e("MainActivity", "000-444444")
//        }
//
//        var coroutineScope = CoroutineScope(Dispatchers.Main)
//        coroutineScope.launch {
//            val m1 = async { getMerge() }
//            val m2 = async(Dispatchers.IO) { getMerge1() }
//            Log.e("MainActivity", "getMerge-coroutineScope" + Thread.currentThread().name)
//            getMerge3(m1.await(), m2.await())
//        }
//        Log.e("MainActivity", "000-5555555")
    }

//    suspend fun getMerge(): String = withContext(Dispatchers.IO) {
//        Log.e("MainActivity", "getMerge" + Thread.currentThread().name)
//        delay(10000)
//        "haha"
//    }
//
//    suspend fun getMerge1(): String {
//        Log.e("MainActivity", "getMerge1" + Thread.currentThread().name)
//        delay(5000)
//        return "haha111"
//    }
//
//    suspend fun getMerge3(name: String, age: String) {
//        delay(5)
//        Log.e("MainActivity", "getMerge3-$name   $age")
//    }
//
//
//    suspend fun getMearge(name: String, age: String) = withContext(Dispatchers.IO) {
//
//    }
//
//    suspend fun getBitmap() = withContext(Dispatchers.IO) {
//        val httpConnection: HttpURLConnection =
//            URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1584425563495&di=b12ce79f13cd973a2db8288d57b3df12&imgtype=0&src=http%3A%2F%2Fcareer.youth.cn%2Fzxzx%2F201309%2FW020130912485635756951.jpg").openConnection() as HttpURLConnection
//        var bitmapDrawable: Drawable? = null
//        if (httpConnection.responseCode == 200) {
//            bitmapDrawable = BitmapDrawable.createFromStream(httpConnection.inputStream, "sdf.png")
//        }
//        Log.e("MainActivity", "000-22222")
//        bitmapDrawable
//    }
//
//
//    fun fill(array: Array<in WeatherNetWork>, weatherNetWork: WeatherNetWork) {
//        array[0] = weatherNetWork
//    }
//
//    fun copy(array1: Array<out WeatherNetWork>, array2: Array<in WeatherNetWork>) {
//        var num = 0;
//        array1.forEach { i ->
//            array2[array2.size + num] = i;
//            num++
//        }
//    }
}
