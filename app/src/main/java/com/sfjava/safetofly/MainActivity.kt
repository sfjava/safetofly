package com.sfjava.safetofly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel by viewModels<WeatherViewModel>()
        viewModel.getWeatherConditions().observe(this, Observer<WeatherConditions>{ weather ->
            // update UI
        })
    }
}
