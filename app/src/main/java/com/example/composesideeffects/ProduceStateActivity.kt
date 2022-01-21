package com.example.composesideeffects

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProduceStateActivity: BaseActivity() {
    @Composable
    override fun LoadComposable() {
        JustProduceState()
    }
}

@Composable
fun JustProduceState() {
    var timerStartStop by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val timer by produceState(initialValue = 0, timerStartStop) {
        val x = (1..10).random()
        var job: Job? = null
        Toast.makeText(context, "Start $x", Toast.LENGTH_SHORT).show()
        if (timerStartStop) {
            // Use MainScope here to ensure awaitDispose is triggered
            job = MainScope().launch {
                while (true) {
                    delay(1000)
                    value++
                }
            }
        }

        awaitDispose {
            Toast.makeText(context, "Done $x", Toast.LENGTH_SHORT).show()
            job?.cancel()
        }
    }

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
}
