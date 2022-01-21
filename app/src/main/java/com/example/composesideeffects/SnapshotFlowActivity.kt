package com.example.composesideeffects

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SnapshotFlowActivity : BaseActivity() {
    @ExperimentalAnimationApi
    @Composable
    override fun LoadComposable() {
        TestSnapshotFlow()
    }
}

@ExperimentalAnimationApi
@Composable
fun TestSnapshotFlow() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val listState = rememberLazyListState()

        LazyColumn(state = listState) {
            items(1000) { index ->
                Text(text = "Item: $index")
            }
        }

        var showButtonSnapshot by remember {
            mutableStateOf(false)
        }

        val showButtonDerive by remember {
            derivedStateOf {
                listState.firstVisibleItemIndex > 0
            }
        }

        Log.d("Track", "Recompose")
        Column {
            AnimatedVisibility(showButtonDerive) {
                Button({}) {
                    Text("Row 1 hiding")
                }
            }
            AnimatedVisibility(showButtonSnapshot) {
                Button({}) {
                    Text("Row 1 and 2 hiding")
                }
            }
        }

        LaunchedEffect(listState) {
            snapshotFlow { listState.firstVisibleItemIndex }
                .map { index -> index > 2 }
                .distinctUntilChanged()
                .collect {
                    // Use MainScope() as workaround
                    // More info as per https://stackoverflow.com/q/70771821/3286489
                    MainScope().launch {
                        Log.d("Track", "B $it")
                        showButtonSnapshot = it
                    }
                }
        }
    }
}
