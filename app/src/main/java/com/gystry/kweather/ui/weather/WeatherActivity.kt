package com.gystry.kweather.ui.weather

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.gystry.kweather.R
import com.gystry.kweather.databinding.ActivityWeatherBinding
import com.gystry.kweather.util.InjectorUtil
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.title.*

class WeatherActivity : AppCompatActivity() {

    val viewModel by lazy {
        ViewModelProviders.of(this, InjectorUtil.getWeatherModelFactory())
            .get(WeatherViewModel::class.java)
    }

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityWeatherBinding>(
            this,
            R.layout.activity_weather
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.statusBarColor = Color.TRANSPARENT
        }
        binding.viewModel = viewModel
        binding.resId = R.color.colorPrimary
        binding.lifecycleOwner = this
        viewModel.weatherId = if (viewModel.isWeatherCached()) {
            viewModel.getCacheWeather().basic.weatherId
        } else {
            intent.getStringExtra("weather_id")
        }
        navButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        viewModel.getWeather()
    }
}
