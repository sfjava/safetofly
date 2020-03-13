package com.sfjava.safetofly

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MockWeatherConditionsRepository: WeatherConditionsRepository {

    private val mockWeatherConditions = WeatherConditions(61.6f)

    private val observableWeatherConditions = MutableLiveData<Result<WeatherConditions>>()

    override fun observeWeatherConditions(): LiveData<Result<WeatherConditions>> {
        return observableWeatherConditions
    }

    override suspend fun getWeatherConditions(forceUpdate: Boolean): Result<WeatherConditions> {
        return Result.Success(mockWeatherConditions)
    }

    override suspend fun refreshWeatherConditions() {
        observableWeatherConditions.postValue(
            getWeatherConditions()
        )
    }
}