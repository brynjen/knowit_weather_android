package no.nordli.brynje.weather.features.weatherloader.presentation.viewmodels

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.nlopez.smartlocation.OnLocationUpdatedListener
import kotlinx.coroutines.launch
import no.nordli.brynje.weather.core.network.presentation.WeatherClient
import no.nordli.brynje.weather.features.weatherloader.domain.LocationRepository
import no.nordli.brynje.weather.features.weatherloader.domain.PreferenceWeather
import java.util.*

class WeatherViewModel(application: Application) : AndroidViewModel(application), OnLocationUpdatedListener {
    private val client = WeatherClient()
    private val preferences = PreferenceWeather(application)
    private val locationRepository = LocationRepository()
    private val packageName = application.packageName

    private val _weatherState : MutableLiveData<WeatherState> = MutableLiveData(CheckingStoredWeather())
    val weatherState : LiveData<WeatherState> = _weatherState

    private fun loadWeather(latitude: Double, longitude: Double) {
        _weatherState.value = LoadingWeather()
        viewModelScope.launch {
            try {
                val weatherResponse = client.api.currentWeather(latitude = latitude, longitude = longitude)
                val weather = weatherResponse.body()
                preferences.saveData(weather = weather!!, time = Date())
                _weatherState.value = LoadedWeatherFromRemote(weather = weather, packageName = packageName)
            } catch (e: Exception) {
                Log.i("w007", "Failed load weather:$e")
                _weatherState.value = FailedLoadingWeather(error = e.toString())
            }
        }
    }

    fun requestWeather(context: Context) {
        _weatherState.value = CheckingStoredWeather()
        val locationDisabled = locationRepository.needsLocationServices(context)
        val permission = ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        if (locationDisabled) {
            // Need to turn gps on
            _weatherState.value = RequestGpsTurnedOn()
        } else {
            if (permission == PackageManager.PERMISSION_GRANTED) {
                // Go for gps
                grantedPermission(context)
            } else {
                _weatherState.value = RequestPermissionToUseLocation()
            }
        }
    }

    fun grantedPermission(context: Context) {
        locationRepository.requestPosition(context, this)
        _weatherState.value = LocationYourPosition()
    }

    override fun onLocationUpdated(location: Location?) {
        Log.i("w007", "Position found: $location")
        if (location != null) {
            try {
                val oldWeather = preferences.lastFetch()
                Log.i("w007", "Last fetch: $oldWeather")
                if (oldWeather != null) {
                    val lat = oldWeather.geometry.coordinates[1]
                    val lon = oldWeather.geometry.coordinates[0]
                    val result = FloatArray(1)
                    Location.distanceBetween(
                        location.latitude,
                        location.longitude,
                        lat,
                        lon,
                        result
                    )
                    if (result[0] < 500) {
                        // Within radius, use old data
                            Log.i("w007", "Within bounds")
                        _weatherState.value = LoadedWeatherFromRemote(weather = oldWeather, packageName = packageName)
                    } else {
                        // Outside radius, get new data
                        Log.i("w007", "Outside bounds")
                        loadWeather(latitude = location.latitude, longitude = location.longitude)
                    }
                } else {
                    Log.i("w007", "Nothing stored")
                    loadWeather(latitude = location.latitude, longitude = location.longitude)
                }
            } catch (e: Exception) {
                Log.i("w007", "Failed stuff:$e")
            }
        }
    }

}