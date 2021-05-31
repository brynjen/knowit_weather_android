package no.nordli.brynje.weather.features.weatherloader.presentation.composables

import android.Manifest
import android.content.Context
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import no.nordli.brynje.weather.features.weatherloader.presentation.viewmodels.WeatherViewModel

@Composable
fun RequestLocationPermissionDialog(context:Context, weatherViewModel: WeatherViewModel = viewModel()) {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            Button(onClick = {
                Permissions.check(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    null,
                    object : PermissionHandler() {
                        override fun onGranted() {
                            weatherViewModel.grantedPermission(context)
                        }
                    },
                )
            }) {
                Text(text = "Gi tillatelse")
            }
        },
        text = { Text(text = "Du trenger posisjonen") },
    )
}