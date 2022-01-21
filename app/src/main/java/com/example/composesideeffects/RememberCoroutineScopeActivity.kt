package com.example.composesideeffects

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RememberCoroutineScopeActivity: BaseActivity() {
    @Composable
    override fun LoadComposable() {
        JustRememberCoroutineScope()
    }
}

@Composable
fun JustRememberCoroutineScope() {
    val scope = rememberCoroutineScope()
    var timer by remember { mutableStateOf(0) }
    var timerStartStop by remember { mutableStateOf(false) }
    var job: Job? by remember { mutableStateOf(null) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Time $timer")
            Button(onClick = {
                timerStartStop = !timerStartStop

                if (timerStartStop) {
                    job?.cancel()
                    job = scope.launch {
                        while (true) {
                            delay(1000)
                            timer++
                        }
                    }
                } else {
                    job?.cancel()
                }

            }) {
                Text(if (timerStartStop) "Stop" else "Start")
            }
        }
    }
}
