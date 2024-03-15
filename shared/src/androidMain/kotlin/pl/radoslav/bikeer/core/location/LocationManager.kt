package pl.radoslav.bikeer.core.location

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOf
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

    @SuppressLint("MissingPermission")
    actual fun getSpeed(): Flow<Float> = callbackFlow {
        val locationRequest = LocationRequest.Builder(100L)
            .setPriority(Priority.PRIORITY_BALANCED_POWER_ACCURACY)
            .build()
        val locationCallback: (Location) -> Unit = { location: Location ->
            trySend(location.speed)
        }
        fuseLocationManager.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
        invokeOnClose {
            fuseLocationManager.removeLocationUpdates(locationCallback)
        }
    }
}