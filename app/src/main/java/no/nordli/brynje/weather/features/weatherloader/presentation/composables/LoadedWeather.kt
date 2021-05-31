package no.nordli.brynje.weather.features.weatherloader.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import no.nordli.brynje.weather.core.network.data.models.TimeSeriesPeriod
import no.nordli.brynje.weather.core.network.data.models.Weather

@Composable
fun LoadedWeather(weather: Weather, packageName: String) {
    val period: TimeSeriesPeriod = weather.properties.timeSeries[0].data.next1Hours!!
    val details: Map<String, Any> = weather.properties.timeSeries[0].data.instant.details!!
    val degrees: Double = details["air_temperature"] as Double
    val resources = LocalContext.current.resources
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(
                id = resources.getIdentifier(
                    period.summary.symbolCode,
                    "mipmap",
                    packageName
                )
            ), contentDescription = period.summary.symbolCode
        )

        Spacer(modifier = Modifier.absolutePadding(top = 20.dp))
        Text(text = "$degrees Celsius")
    }
}