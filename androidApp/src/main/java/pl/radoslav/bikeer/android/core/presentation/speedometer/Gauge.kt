package pl.radoslav.bikeer.android.core.presentation.speedometer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Gauge(
    modifier: Modifier = Modifier,
    minValue: Float = 0f,
    maxValue: Float = 50f,
    value: Float = 25f,
    fontSize: TextUnit = 50.sp
) {
    Box(
        modifier = modifier
            .defaultMinSize(minWidth = 160.dp, minHeight = 160.dp)
    ) {
        val maxSweep = 250f
        val sweep = (value - minValue) / maxValue * maxSweep
        Canvas(modifier = Modifier.matchParentSize()) {
            drawArc(Color.LightGray, 145f, sweep, true)
            drawCircle(Color.Gray, radius = this.size.width / 2 * 0.85f)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = "${value.toInt()}", fontSize = fontSize)
            Text(text = "KM/H", fontSize = fontSize * 2 / 5)
        }
    }
}

@Preview
@Composable
private fun GaugePreview() {
    Gauge(value = 6f)
}