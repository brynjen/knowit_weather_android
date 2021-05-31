package no.nordli.brynje.weather.features.weatherloader.presentation.pages

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import no.nordli.brynje.weather.features.weatherloader.presentation.composables.*
import no.nordli.brynje.weather.features.weatherloader.presentation.viewmodels.*

@Composable
fun WeatherLoaderPage(
    weatherViewModel: WeatherViewModel = viewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
) {
    val appState = weatherViewModel.weatherState.observeAsState()
    val context = LocalContext.current
    Scaffold(scaffoldState = scaffoldState) {
        when (appState.value) {
            is CheckingStoredWeather -> {
                CheckingLocalStorage()
            }
            is LoadingWeather -> {
                LoadingRemoteWeather()
            }
            is LoadedWeatherFromRemote -> {
                val existingWeather = appState.value as LoadedWeatherFromRemote
                LoadedWeather(weather = existingWeather.weather, packageName = existingWeather.packageName)
            }
            is FailedLoadingWeather -> {
                val errorState = appState.value as FailedLoadingWeather
                FailedToLoadWeather(error = errorState.error)
            }
            is RequestGpsTurnedOn -> {
                RequestLocationSettingsDialog(context)
            }
            is RequestPermissionToUseLocation -> {
                RequestLocationPermissionDialog(context)
            }
            is LocationYourPosition -> {
                FindingYourPosition()
            }
        }
    }
}