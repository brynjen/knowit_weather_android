package no.nordli.brynje.weather.core.network.data.models

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName(value = "type") val type: String,
    @SerializedName(value = "geometry") val geometry: Geometry,
    @SerializedName(value = "properties") val properties: Properties,
)