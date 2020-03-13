package com.sfjava.safetofly

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class WeatherConditionsViewModel(val repository: WeatherConditionsRepository): ViewModel() {

    private val _forceUpdate = MutableLiveData<Boolean>(false)

    private val _weatherConditions: LiveData<WeatherConditions> = _forceUpdate.switchMap { forceUpdate ->
        if (forceUpdate) {
            _dataLoading.value = true
            viewModelScope.launch {
                repository.refreshWeatherConditions()
                _dataLoading.value = false
            }
        }
        repository.observeWeatherConditions().distinctUntilChanged().switchMap { processResult(it) }
    }
    val weatherConditions: LiveData<WeatherConditions> = _weatherConditions

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    init {
        loadWeatherConditions(true)
    }

    fun loadWeatherConditions(forceUpdate: Boolean) {
        _forceUpdate.value = forceUpdate
    }

    private fun processResult(itemsResult: Result<WeatherConditions>): LiveData<WeatherConditions> {
        // TODO: this is a good case for liveData builder; replace when stable (per google's sample)
        val result = MutableLiveData<WeatherConditions>()
        if (itemsResult is Result.Success) {
            // isDataLoadingError.value = false
            viewModelScope.launch {
                result.value = itemsResult.data
            }
        } else {
            result.value = WeatherConditions((0.0f)) // FIXME
            // showSnackbarMessage(R.string.loading_tasks_error)
            // isDataLoadingError.value = true
        }
        return result
    }
}
