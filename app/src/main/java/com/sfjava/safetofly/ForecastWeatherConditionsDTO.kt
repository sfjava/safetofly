package com.sfjava.safetofly

import kotlinx.serialization.Serializable

@Serializable
data class ForecastWeatherConditionsDTO(
    val currently: CurrentConditionsDTO
)

@Serializable
data class CurrentConditionsDTO(
    // "time": 1509993277,
    // "summary": "Drizzle",
    // "icon": "rain",
    // "nearestStormDistance": 0,
    // "precipIntensity": 0.0089,
    // "precipIntensityError": 0.0046,
    // "precipProbability": 0.9,
    // "precipType": "rain",
    val temperature: Float
    //"apparentTemperature": 66.31,
    //"dewPoint": 60.77,
    //"humidity": 0.83,
    //"pressure": 1010.34,
    //"windSpeed": 5.59,
    //"windGust": 12.03,
    //"windBearing": 246,
    //"cloudCover": 0.7,
    //"uvIndex": 1,
    //"visibility": 9.84,
    //"ozone": 267.44
)

//
// transform DTO to Entity used in ViewModel
//
fun ForecastWeatherConditionsDTO.toWeatherConditions(): WeatherConditions {
    return WeatherConditions(currently.temperature)
}