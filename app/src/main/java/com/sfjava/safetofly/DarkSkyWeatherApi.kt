package com.sfjava.safetofly

import retrofit2.http.GET
import retrofit2.http.Path

interface DarkSkyWeatherApi {

    /**
     * retrieve forecast weather conditions: https://api.darksky.net/forecast/[key]/[latitude],[longitude]
     **/
    @GET(value = "forecast/{apiKey}/{latitude},{longitude}")
    suspend fun retrieveForecastWeatherConditions(
        @Path("apiKey") apiKey: String,
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double
    ): ForecastWeatherConditionsDTO
}