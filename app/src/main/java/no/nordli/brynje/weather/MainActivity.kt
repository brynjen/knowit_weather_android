package no.nordli.brynje.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.lifecycle.lifecycleScope
import no.nordli.brynje.weather.features.weatherloader.presentation.viewmodels.WeatherViewModel
import no.nordli.brynje.weather.ui.theme.WeatherTheme
import no.nordli.brynje.weather.features.weatherloader.presentation.pages.WeatherLoaderPage


class MainActivity : ComponentActivity() {
    private val weatherViewModel : WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApp()
        }
    }

    init {
        lifecycleScope.launchWhenResumed {
            weatherViewModel.requestWeather(applicationContext)
        }
    }
}

@Composable
fun WeatherApp() {
    WeatherTheme {
        Surface(color = MaterialTheme.colors.background) {
            WeatherLoaderPage(
                scaffoldState = rememberScaffoldState(),
            )
        }
    }
}
