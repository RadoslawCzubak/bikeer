package pl.radoslav.bikeer.android.speedometer.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.radoslav.bikeer.android.R
import pl.radoslav.bikeer.android.core.presentation.speedometer.Gauge
import pl.radoslav.bikeer.speedometer.presentation.SpeedometerState
import pl.radoslav.bikeer.speedometer.presentation.SpeedometerViewModel

@Composable
fun SpeedometerScreen(
    viewModel: SpeedometerViewModel
) {
    val state by viewModel.state.collectAsState()
    SpeedometerScreenContent(state)
}

@Composable
fun SpeedometerScreenContent(
    state: SpeedometerState,
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
            value = state.speed,
            minValue = 0f,
            maxValue = 50f,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Map(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            )
            Spacer(modifier = Modifier.size(8.dp))
            NavigationInfo(
                latitude = state.latitude,
                longitude = state.longitude,
                altitude = state.altitude,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            )
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
    latitude: Double,
    longitude: Double,
    altitude: Double,
    modifier: Modifier = Modifier
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
    iconId: Int,
    stringValue: String,
    modifier: Modifier = Modifier
) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = stringValue,
            style = TextStyle(
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
        SpeedometerState()
    )
}