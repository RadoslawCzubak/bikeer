package pl.radoslav.bikeer.speedometer.presentation

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.radoslav.bikeer.speedometer.domain.LocationRepository

open class SpeedometerViewModel(
    private val locationRepository: LocationRepository
) : KMMViewModel() {
    private val _state = MutableStateFlow(viewModelScope, SpeedometerState())

    @NativeCoroutinesState
    val state = _state

    fun onEvent(speedometerEvent: SpeedometerEvent) {
        when (speedometerEvent) {
            is SpeedometerEvent.CheckLocation -> {
                viewModelScope.coroutineScope.launch {
                    _state.update { state ->
                        locationRepository.getCurrentLocation().let {
                            state.copy(
                                lat = it.latitude,
                                lon = it.longitude,
                            )
                        }
                    }
                }
            }
        }
    }
}