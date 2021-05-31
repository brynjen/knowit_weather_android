package no.nordli.brynje.weather.features.weatherloader.presentation.composables

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FailedToLoadWeather(error: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Feilet å hente værdata:$error")
        Spacer(modifier = Modifier.absolutePadding(top = 20.dp))
        Button(onClick = {
            Log.i("w007", "Clicked button ,retry")
            //weatherViewModel.requestWeather()
        }) { Text(text = "Prøv igjen") }
    }
}