package com.sfjava.safetofly

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Call
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class DarkSkyWeatherConditionsRepository() : WeatherConditionsRepository {

    private val baseUrl = "https://api.darksky.net/"
    private val apiKey = "eee24d32820d02ac1c18feae1b1095f6" // FIXME: pull from build config
    private val currentLatitude = 37.8267                   // FIXME: pull from GPS / user-settings
    private val currentLongitude = -122.4233

    val api by lazy { retrofit(okhttp(), baseUrl).create(DarkSkyWeatherApi::class.java) }

    private val observableWeatherConditions = MutableLiveData<Result<WeatherConditions>>()

    override fun observeWeatherConditions(): LiveData<Result<WeatherConditions>> {
        return observableWeatherConditions
    }

    override suspend fun getWeatherConditions(forceUpdate: Boolean): Result<WeatherConditions> {
        val weatherConditions =
            api.retrieveForecastWeatherConditions(
                    apiKey,
                    currentLatitude,
                    currentLongitude
            ).toWeatherConditions()

        return Result.Success(weatherConditions) // TODO: handle exceptions as Result.Error ??
    }

    override suspend fun refreshWeatherConditions() {
        observableWeatherConditions.postValue(
            getWeatherConditions()
        )
    }

    @OptIn(UnstableDefault::class)
    private fun retrofit(callFactory: Call.Factory, baseUrl: String) = Retrofit.Builder()
        .callFactory(callFactory)
        .baseUrl(baseUrl)
        .addConverterFactory(
            Json(JsonConfiguration(strictMode = false))
                .asConverterFactory("application/json".toMediaType())
        )
        .build()

    private fun okhttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(loggingInterceptor)

        return builder.build()
    }
}