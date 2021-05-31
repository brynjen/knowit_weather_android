package no.nordli.brynje.weather.core.network.data.models

import com.google.gson.annotations.SerializedName

data class TimeSeriesInstant(@SerializedName(value = "details") val details: Map<String, Any>?)