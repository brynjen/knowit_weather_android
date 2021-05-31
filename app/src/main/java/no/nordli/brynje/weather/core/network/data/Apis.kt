package no.nordli.brynje.weather.core.network.data

/**
Apis to connect to the different parts of the weather api
 */
class Apis {
    companion object {
        /** Base url for weather **/
        const val baseUrl = "https://api.met.no/weatherapi/"

        /** Weather compact url **/
        const val weatherCompact = "locationforecast/2.0/compact"
    }
}