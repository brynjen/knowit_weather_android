package no.nordli.brynje.weather.core.network.data.models

import com.google.gson.annotations.SerializedName

data class TimeSeriesData(
    @SerializedName(value = "instant") val instant: TimeSeriesInstant,
    @SerializedName(value = "next_1_hours") val next1Hours: TimeSeriesPeriod?,
)
