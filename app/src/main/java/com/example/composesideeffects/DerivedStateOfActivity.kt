package com.example.composesideeffects

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase

class DerivedStateOfActivity: BaseActivity() {
    @Composable
    override fun LoadComposable() {
        JustDerivedState()
    }
}

@Composable
fun JustDerivedState() {

    Log.d("Track", "Recomposing")

    var name by remember { mutableStateOf("") }
    var otherName by remember { mutableStateOf("") }
    val hello by remember {
        Log.d("Track", "In remember")
        derivedStateOf {
            val lowerCase = name.toLowerCase(Locale.current)
            Log.d("Track", "In derivedState")
            "Hello, ${otherName.toLowerCase(Locale.current)} and $lowerCase!"
        }
    }

    Texts(name, otherName, hello, { name = it }, { otherName = it})
}

@Composable
private fun Texts(name: String, otherName: String, hello: String,
                   onNameChange: (String) -> Unit,
                   onOtherNameChange: (String) -> Unit
) {
    Log.d("Track", "Recomposing Texts")
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = name,
            onValueChange = onNameChange
        )
        TextField(
            value = otherName,
            onValueChange = onOtherNameChange
        )
        Text(name)
        Text(otherName)
        Text(hello)
    }
}
