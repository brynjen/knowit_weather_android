package no.nordli.brynje.weather.core.network.data.models

import com.google.gson.annotations.SerializedName

data class TimeSeriesPeriod(@SerializedName(value = "summary") val summary: TimeSeriesSummary)
