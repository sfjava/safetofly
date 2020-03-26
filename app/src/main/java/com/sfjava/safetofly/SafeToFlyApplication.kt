package com.sfjava.safetofly;

import android.app.Application;

class SafeToFlyApplication: Application() {

    val weatherConditionsRepository by lazy {
        DarkSkyWeatherConditionsRepository() // MockWeatherConditionsRepository()
    }

    // NOTE: repo impl could depend here on the app flavor... (e.g. for mocking)
    // fun weatherConditionsRepository = ServiceLocator.provideFeedItemsRepository(this, feedType)

    // override fun onCreate() {
    //    super.onCreate()
    // }
}
