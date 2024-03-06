package pl.radoslav.bikeer.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.radoslav.bikeer.android.examplefeature.presentation.ExampleFeatureScreen
import pl.radoslav.bikeer.android.speedometer.presentation.SpeedometerScreen
import pl.radoslav.bikeer.examplefeature.presentation.CounterViewModel
import pl.radoslav.bikeer.speedometer.presentation.SpeedometerViewModel

class MainActivity : ComponentActivity() {
    private val counterViewModel: CounterViewModel by viewModels()
    private val speedometerViewModel: SpeedometerViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                SpeedometerScreen(viewModel = speedometerViewModel)
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
