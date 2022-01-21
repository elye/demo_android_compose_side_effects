package com.example.composesideeffects

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

class LaunchedEffectActivity : BaseActivity() {
    @Composable
    override fun LoadComposable() {
        JustLaunchEffect()
    }
}


@Composable
fun JustLaunchEffect() {
    var timer by remember { mutableStateOf(0) }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Text("Time $timer")
    }

    LaunchedEffect(key1 = Unit) {
        while (true) {
            delay(1000)
            timer++
        }
    }
}
