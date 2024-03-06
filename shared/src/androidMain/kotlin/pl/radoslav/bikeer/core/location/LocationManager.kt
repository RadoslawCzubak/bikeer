package pl.radoslav.bikeer.core.location

import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

actual class LocationManager(
    private val fuseLocationManager: FusedLocationProviderClient
) {
    @SuppressLint("MissingPermission")
    actual suspend fun getCurrentLocation(): GpsLocation {
        return suspendCoroutine {
            fuseLocationManager.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener { location ->
                    it.resume(
                        GpsLocation(
                            latitude = location.latitude,
                            longitude = location.longitude,
                            altitude = location.altitude,
                            speed = location.speed.toDouble()
                        )
                    )
                }
                .addOnFailureListener { e ->
                    throw e
                }
        }
    }
}