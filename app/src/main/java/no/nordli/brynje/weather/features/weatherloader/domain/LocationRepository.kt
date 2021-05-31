package no.nordli.brynje.weather.features.weatherloader.domain

import android.content.Context
import io.nlopez.smartlocation.OnLocationUpdatedListener
import io.nlopez.smartlocation.SmartLocation

/** Handles location and all things related to it **/
class LocationRepository {

    /** Finds the position of the user **/
    fun requestPosition(context: Context, listener: OnLocationUpdatedListener) {
        SmartLocation.with(context).location().oneFix().start(listener)
    }

    fun needsLocationServices(context: Context): Boolean {
        return !SmartLocation.with(context).location().state().locationServicesEnabled()
    }
}