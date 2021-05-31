package no.nordli.brynje.weather.core.network.domain

import no.nordli.brynje.weather.core.network.data.Apis
import no.nordli.brynje.weather.core.network.data.models.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(Apis.weatherCompact)
    suspend fun currentWeather(
        @Query(value = "lat") latitude: Double,
        @Query(value = "lon") longitude: Double,
    ): Response<Weather>
}