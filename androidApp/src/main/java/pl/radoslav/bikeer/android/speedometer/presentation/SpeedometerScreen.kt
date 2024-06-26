package pl.radoslav.bikeer.android.speedometer.presentation

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import pl.radoslav.bikeer.android.R
import pl.radoslav.bikeer.android.core.presentation.speedometer.Gauge
import pl.radoslav.bikeer.speedometer.presentation.SpeedometerEvent
import pl.radoslav.bikeer.speedometer.presentation.SpeedometerState
import pl.radoslav.bikeer.speedometer.presentation.SpeedometerViewModel

@Composable
fun SpeedometerScreen(
    viewModel: SpeedometerViewModel
) {
    val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val permissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions.values.all { it }) {
                viewModel.onEvent(SpeedometerEvent.ObserveLocation)
            } else {
                viewModel.onEvent(SpeedometerEvent.OnNoLocationPermission)
            }
        }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = Unit) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    permissionLauncher.launch(locationPermissions)
                }

                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { }
    }
    val state by viewModel.state.collectAsState()
    when (state) {
        is SpeedometerState.SpeedometerAvailable -> {
            val stateSpeedometerAvailable: SpeedometerState.SpeedometerAvailable =
                state as SpeedometerState.SpeedometerAvailable
            SpeedometerScreenContent(
                stateSpeedometerAvailable.latitude,
                longitude = stateSpeedometerAvailable.longitude,
                altitude = stateSpeedometerAvailable.altitude,
                speed = stateSpeedometerAvailable.speed
            )
        }

        is SpeedometerState.SpeedometerNotAvailable -> {
            Text(text = "Speedometer not available")
        }

        is SpeedometerState.SpeedometerError -> {
            Text(text = "Error: ${(state as SpeedometerState.SpeedometerError).message}")
        }

        is SpeedometerState.SpeedometerPermissionNotGranted -> {
            Column {
                Text(text = "Permission not granted: ${(state as SpeedometerState.SpeedometerPermissionNotGranted).message}")
                Button(onClick = { permissionLauncher.launch(locationPermissions) }) {
                    Text(text = "Retry")
                }
            }
        }

        SpeedometerState.Initialized -> SpeedometerScreenContent(
            waitingForFirstUpdate = true
        )
    }
}

@Composable
fun SpeedometerScreenContent(
    latitude: Double = 0.0,
    longitude: Double = 0.0,
    altitude: Double = 0.0,
    speed: Float = 0f,
    waitingForFirstUpdate: Boolean = false
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Gauge(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(48.dp),
            value = speed,
            minValue = 0f,
            maxValue = 50f,
            baseColor = Color(0xFF1C1C1E),
            progressColor = Color(0xFF0094FF),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            if (waitingForFirstUpdate) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Gray)
                        .weight(1f)
                        .aspectRatio(1f)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                )
            } else {
                Map(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                )
                Spacer(modifier = Modifier.size(8.dp))
                NavigationInfo(
                    latitude = latitude,
                    longitude = longitude,
                    altitude = altitude,
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                )
            }
        }
    }
}

@Composable
fun Map(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Gray)
    )
}

@Composable
fun NavigationInfo(
    latitude: Double, longitude: Double, altitude: Double, modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        NavigationItem(
            iconId = R.drawable.ic_latitude,
            stringValue = "%.5f".format(latitude),
            modifier = Modifier.fillMaxWidth()
        )
        NavigationItem(
            iconId = R.drawable.ic_longitude,
            stringValue = "%.5f".format(longitude),
            modifier = Modifier.fillMaxWidth()
        )
        NavigationItem(
            iconId = R.drawable.ic_altitude,
            stringValue = "%.2f".format(altitude),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun NavigationItem(
    iconId: Int, stringValue: String, modifier: Modifier = Modifier
) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = stringValue, style = TextStyle(
                color = Color.White,
            )
        )
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun SpeedometerScreenContentPreview() {
    SpeedometerScreenContent(
        latitude = 0.0, longitude = 0.0, altitude = 0.0, speed = 0f, waitingForFirstUpdate = true
    )
}