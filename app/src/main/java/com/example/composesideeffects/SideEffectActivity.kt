package com.example.composesideeffects

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class SideEffectActivity: BaseActivity() {
    @Composable
    override fun LoadComposable() {
        TrySideEffect()
    }
}

@Composable
fun TrySideEffect() {
    var timer by remember { mutableStateOf(0) }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Text("Time $timer")
    }

    SideEffect {
        Thread.sleep(1000)
        timer++
    }

    Thread.sleep(1000)
    timer++
}
