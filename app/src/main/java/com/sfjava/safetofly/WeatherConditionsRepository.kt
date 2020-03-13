package com.sfjava.safetofly

import androidx.lifecycle.LiveData

interface WeatherConditionsRepository {

    fun observeWeatherConditions(): LiveData<Result<WeatherConditions>>

    suspend fun getWeatherConditions(forceUpdate: Boolean = false): Result<WeatherConditions>

    suspend fun refreshWeatherConditions()
}