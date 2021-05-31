package no.nordli.brynje.weather.features.weatherloader.presentation.composables

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun RequestLocationSettingsDialog(context: Context) {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            Button(onClick = {
                context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }) {
                Text(text = "Åpne GPS settings")
            }
        },
        text = { Text(text = "Du trenger å skru på lokasjoner") },
    )
}