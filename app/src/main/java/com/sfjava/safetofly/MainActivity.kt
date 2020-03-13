package com.sfjava.safetofly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sfjava.safetofly.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.setLifecycleOwner(this)  // LiveData needs the lifecycle owner
        setContentView(binding.root)

        val weatherConditionsViewModel by viewModels<WeatherConditionsViewModel> { getViewModelFactory() }
        // weatherConditionsViewModel.weatherConditions.observe(this, Observer<WeatherConditions> { weather ->
        //    // indicate that weather has been updated, say, in a toast?
        // })

        // bind the layout with our ViewModel
        binding.viewmodel = weatherConditionsViewModel
    }

    private fun getViewModelFactory(): ViewModelFactory {
        val repository = (application as SafeToFlyApplication).weatherConditionsRepository
        return ViewModelFactory(repository, this)
    }
}

