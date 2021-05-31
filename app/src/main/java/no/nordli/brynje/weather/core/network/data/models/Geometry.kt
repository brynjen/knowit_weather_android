package no.nordli.brynje.weather.core.network.data.models

import com.google.gson.annotations.SerializedName

data class Geometry (@SerializedName(value= "coordinates") val coordinates: List<Double>)