package pl.radoslav.bikeer.core.location

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import platform.CoreLocation.CLLocationManager

actual class LocationManager {
    actual suspend fun getCurrentLocation(): GpsLocation {
        return GpsLocation(0.0, 0.0, 0.0, 0.0)
    }
}