package pl.radoslav.bikeer.android.core.presentation.speedometer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
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
    fontSize: TextUnit = 50.sp,
    baseColor: Color = Color.Gray,
    progressColor: Color = Color.LightGray,
    textColor: Color = Color.White
) {
    Box(
        modifier = modifier
            .defaultMinSize(minWidth = 160.dp, minHeight = 160.dp)
    ) {
        val maxSweep = 250f
        val sweep = (value - minValue) / maxValue * maxSweep
        Canvas(modifier = Modifier.matchParentSize()) {
            val stroke = Stroke(width = this.size.width / 2 * 0.1f, cap = StrokeCap.Round)
            drawCircle(baseColor, radius = this.size.width / 2 * 0.85f)
            drawArc(
                progressColor,
                145f,
                sweep,
                false,
                style = stroke,
                size = size * 0.85f,
                topLeft = Offset(0.15f * size.width / 2, 0.15f * size.height / 2)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = "${value.toInt()}", fontSize = fontSize, color = textColor)
            Text(text = "KM/H", fontSize = fontSize * 2 / 5, color = textColor)
        }
    }
}

@Preview
@Composable
private fun GaugePreview() {
    Gauge(value = 50f, modifier = Modifier.size(150.dp))
}