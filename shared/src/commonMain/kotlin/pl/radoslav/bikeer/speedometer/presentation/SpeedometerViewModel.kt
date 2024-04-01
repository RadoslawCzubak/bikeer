package pl.radoslav.bikeer.speedometer.presentation

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.radoslav.bikeer.speedometer.domain.LocationRepository

open class SpeedometerViewModel(
    private val locationRepository: LocationRepository
) : KMMViewModel() {
    private val _state: MutableStateFlow<SpeedometerState> =
        MutableStateFlow(viewModelScope, SpeedometerState.SpeedometerAvailable())

    @NativeCoroutinesState
    val state = _state

    fun onEvent(speedometerEvent: SpeedometerEvent) {
        when (speedometerEvent) {
            is SpeedometerEvent.ObserveLocation -> {
                viewModelScope.coroutineScope.launch {
                    withContext(Dispatchers.IO){
                        locationRepository.observeLocation()
                            .collect { location ->
                                _state.update {
                                    SpeedometerState.SpeedometerAvailable(
                                        latitude = location.latitude,
                                        longitude = location.longitude,
                                        altitude = location.altitude,
                                        speed = location.speed.toFloat(),
                                    )
                                }
                            }
                    }
                }
            }

            is SpeedometerEvent.OnNoLocationPermission -> {
                _state.update {
                    SpeedometerState.SpeedometerPermissionNotGranted()
                }
            }

            else -> Unit
        }
    }
}