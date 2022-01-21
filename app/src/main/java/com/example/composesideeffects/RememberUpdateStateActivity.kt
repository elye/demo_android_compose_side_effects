package com.example.composesideeffects

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class RememberUpdateStateActivity: BaseActivity() {
    @Composable
    override fun LoadComposable() {
        JustRememberUpdatedState()
    }
}

@Composable
fun JustRememberUpdatedState() {
    var timer by remember { mutableStateOf(0) }
    var timerStartStop by remember { mutableStateOf(false) }
    var launchCaptureValue by remember { mutableStateOf(0) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TimerOfTextField(timerStartStop, timer, {
                timer = stringToInt(it)
            }, {
                timer++
                launchCaptureValue = it
            })
            Button(onClick = {
                timerStartStop = !timerStartStop
            }) {
                Text(if (timerStartStop) "Stop" else "Start")
            }
            Text("Launched Captured Value: $launchCaptureValue")
        }
    }
}

private fun stringToInt(it: String) = try {
    Integer.parseInt(it)
} catch (ex: Exception) {
    0
}

@Composable
fun TimerOfTextField(
    timerStartStop: Boolean,
    timer: Int,
    onValueChanged: (String) -> Unit,
    onLaunchEffect: (Int) -> Unit
) {
    if (timerStartStop) {
        Text("Time $timer")
    } else {
        TextField(
            value = timer.toString(),
            onValueChange = onValueChanged,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.width(128.dp),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
        )
    }
    val myRememberTimer by rememberUpdatedState(timer)
    LaunchedEffect(key1 = timerStartStop) {
        while (timerStartStop) {
            delay(1000)
            onLaunchEffect(myRememberTimer)
        }
    }
}
