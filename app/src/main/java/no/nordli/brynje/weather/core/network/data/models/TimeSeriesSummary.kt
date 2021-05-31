package no.nordli.brynje.weather.core.network.data.models

import com.google.gson.annotations.SerializedName

data class TimeSeriesSummary (@SerializedName(value = "symbol_code") val symbolCode: String)