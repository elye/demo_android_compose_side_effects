package com.example.composesideeffects

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.composesideeffects.ui.theme.ComposeSideEffectsTheme

class MainActivity : BaseActivity() {
    @Composable
    override fun LoadComposable() {
        Launcher()
    }
}

@Composable
fun Launcher() {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { context.StartActivityButton(LaunchedEffectActivity::class.java) }
        item { context.StartActivityButton(SideEffectActivity::class.java) }
        item { context.StartActivityButton(ProduceStateActivity::class.java) }
        item { context.StartActivityButton(ControllableLaunchedEffectActivity::class.java) }
        item { context.StartActivityButton(CancelDetectLaunchedEffectActivity::class.java) }
        item { context.StartActivityButton(RememberCoroutineScopeActivity::class.java) }
        item { context.StartActivityButton(RememberUpdateStateActivity::class.java) }
        item { context.StartActivityButton(DisposableEffectActivity::class.java) }
        item { context.StartActivityButton(DerivedStateOfActivity::class.java) }
        item { context.StartActivityButton(SnapshotFlowActivity::class.java) }
    }
}

@Composable
private fun <T : Activity> Context.StartActivityButton(clazz: Class<T>) {
    Button(onClick = { startActivity(Intent(this, clazz)) }) {
        Text(clazz.simpleName ?: "")
    }
}
