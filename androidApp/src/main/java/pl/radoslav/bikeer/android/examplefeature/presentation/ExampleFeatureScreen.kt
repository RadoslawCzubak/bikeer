package pl.radoslav.bikeer.android.examplefeature.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import pl.radoslav.bikeer.examplefeature.presentation.CounterEvent
import pl.radoslav.bikeer.examplefeature.presentation.CounterViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExampleFeatureScreen(
    viewModel: CounterViewModel
) {
    val state by viewModel.state.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            "Counter: ${state.counter}",
            style = TextStyle(
                fontSize = 24.sp,
                color = Color.White
            )
        )
        Spacer(modifier = Modifier.height(64.dp))
        Row {
            Button(onClick = { viewModel.onEvent(CounterEvent.Decrement) }) {
                Text(text = "Decrement")
            }
            Button(onClick = { viewModel.onEvent(CounterEvent.Increment) }) {
                Text(text = "Increment")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ExampleFeatureScreenPreview() {
    ExampleFeatureScreen(viewModel = CounterViewModel())
}