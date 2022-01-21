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
import kotlinx.coroutines.delay

class CancelDetectLaunchedEffectActivity: BaseActivity() {
    @Composable
    override fun LoadComposable() {
        CancelDetectLaunchedEffect()
    }
}

@Composable
fun CancelDetectLaunchedEffect() {
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

    val context = LocalContext.current

    LaunchedEffect(key1 = timerStartStop) {
        val x = (1..10).random()
        try {
            while (timerStartStop) {
                delay(1000)
                timer++
            }
        } catch (ex: Exception) {
            Toast.makeText(context, "Oh No $x", Toast.LENGTH_SHORT).show()
        } finally {
            Toast.makeText(context, "Done $x", Toast.LENGTH_SHORT).show()
        }
    }
}
