package com.example.composesideeffects

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

class ControllableLaunchedEffectActivity : BaseActivity() {
    @Composable
    override fun LoadComposable() {
        ControllableLaunchEffect()
    }
}

@Composable
fun ControllableLaunchEffect() {
    var timer by remember { mutableStateOf(0) }
    var timerStartStop by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Time $timer")
            Button(onClick = {
                timerStartStop = !timerStartStop
            }) {
                Text(if (timerStartStop) "Stop" else "Start")
            }
        }
    }

    LaunchedEffect(key1 = timerStartStop) {
        while (timerStartStop) {
            delay(1000)
            timer++
        }
    }
}
