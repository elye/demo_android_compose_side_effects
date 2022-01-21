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

class DisposableEffectActivity : BaseActivity() {
    @Composable
    override fun LoadComposable() {
        JustDisposableEffect()
    }
}

@Composable
fun JustDisposableEffect() {
    var timerStartStop by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                timerStartStop = !timerStartStop
            }) {
                Text(if (timerStartStop) "Stop" else "Start")
            }
        }
    }

    val context = LocalContext.current

    DisposableEffect(key1 = timerStartStop) {
        val x = (1..10).random()
        Toast.makeText(context, "Start Disposable $x", Toast.LENGTH_SHORT).show()

        onDispose {
            Toast.makeText(context, "Stop Disposable $x", Toast.LENGTH_SHORT).show()
        }
    }
}
