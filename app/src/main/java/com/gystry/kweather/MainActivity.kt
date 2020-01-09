package com.gystry.kweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gystry.kweather.net.OkHttpManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_content.setOnClickListener {
                        OkHttpManager.getInstance.requestToGet()
//            val map: HashMap<String, String> = HashMap()
//            map.put("id","2334")
//            OkHttpManager.getInstance.requestToPost(map)
        }
    }
}
