package no.nordli.brynje.weather.features.weatherloader.domain

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import no.nordli.brynje.weather.core.network.data.models.Weather
import java.util.*

/** Stores and retrieves from Preferences **/
class PreferenceWeather(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("WeatherStore", Context.MODE_PRIVATE)
    private val difference = 1000*60*5 // 5 minutes
    /** Fetches last stored data **/
    fun lastFetch(): Weather? {
        var lastTime = sharedPreferences.getLong("time", 0L)
        val lastData = sharedPreferences.getString("data", null)
        if (lastTime != 0L && lastData != null) {
            // Old data found
            val weather = Gson().fromJson<Weather>(lastData, object : TypeToken<Weather>() {}.type)
            lastTime += difference
            val oldDate = Date()
            oldDate.time = lastTime
            return if (oldDate.before(Date())) {
                // Timed out, get new weather
                null
            } else {
                // Still valid, use old weather
                weather
            }
        } else {
            // No data found, get new weather
            return null;
        }
    }

    /** Saves a newly fetched weather data **/
    fun saveData(weather: Weather, time: Date) {
        val edit = sharedPreferences.edit()
        val gson = Gson().toJson(weather)
        edit.putLong("time", time.time)
        edit.putString("data", gson.toString())
        edit.apply()
    }
}

/*


*/