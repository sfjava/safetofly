package com.sfjava.safetofly

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel: ViewModel() {

    fun getWeatherConditions(): LiveData<WeatherConditions> {
        return MutableLiveData<WeatherConditions>() // TODO
    }
}
