package pl.radoslav.bikeer.android.speedometer.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import pl.radoslav.bikeer.speedometer.presentation.SpeedometerEvent
import pl.radoslav.bikeer.speedometer.presentation.SpeedometerViewModel

@Composable
fun SpeedometerScreen(
    viewModel: SpeedometerViewModel
) {
    val state by viewModel.state.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Lat: ${state.lat} Lon: ${state.lon}",
            style = TextStyle(fontSize = 24.sp, color = Color.White)
        )
        Button(onClick = { viewModel.onEvent(SpeedometerEvent.CheckLocation) }) {
            Text(text = "Check location")
        }
    }
}
