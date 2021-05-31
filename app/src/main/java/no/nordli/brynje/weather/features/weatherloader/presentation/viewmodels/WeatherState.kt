package no.nordli.brynje.weather.features.weatherloader.presentation.viewmodels

import no.nordli.brynje.weather.core.network.data.models.Weather

abstract class WeatherState

class CheckingStoredWeather : WeatherState()

class LoadingWeather: WeatherState()

class LocationYourPosition: WeatherState()

class FailedLoadingWeather(val error:String) : WeatherState()

class LoadedWeatherFromRemote(val weather:Weather, val packageName:String) : WeatherState()

// Gps is not turned on device
class RequestGpsTurnedOn : WeatherState()

// No permission to use gps, ask for it
class RequestPermissionToUseLocation : WeatherState()