package pl.radoslav.bikeer.core.location

import kotlinx.cinterop.useContents
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationAccuracy
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.CLRegion
import platform.Foundation.NSError
import platform.darwin.NSObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

actual class LocationManager() : NSObject() {

    private val locationManager = CLLocationManager()

    actual suspend fun getCurrentLocation(): GpsLocation {
        print("Getting location")
        return suspendCoroutine { cont ->
            locationManager.delegate = object : NSObject(), CLLocationManagerDelegateProtocol {
                override fun locationManager(
                    manager: CLLocationManager,
                    didUpdateLocations: List<*>
                ) {
                    print("Location received")
                    val lastLocation = didUpdateLocations.first() as CLLocation
                    val mappedLocation = lastLocation.coordinate.useContents {
                        GpsLocation(latitude, longitude, 0.0, 0.0)
                    }
                    print("Location: $mappedLocation")
                    cont.resume(mappedLocation)
                }

                override fun locationManager(
                    manager: CLLocationManager,
                    didFailWithError: NSError
                ) {
                    print("Failed to get location")
                    cont.resumeWithException(RuntimeException("Failed to get location"))
                }

                override fun locationManager(
                    manager: CLLocationManager,
                    didChangeAuthorizationStatus: Int
                ) {
                    print("Authorization status changed")
                }

                override fun locationManager(
                    manager: CLLocationManager,
                    monitoringDidFailForRegion: CLRegion?,
                    withError: NSError
                ) {
                    print("Monitoring failed")
                }
            }
            locationManager.requestLocation()
        }
    }
}

