// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.io.File

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Row {
            Column {
                Text("Tracking Simulation")
            }
            Column {
                Button(onClick = {
                    text = "Welcome!!"
                }) {
                    Text(text)
                }
            }

        }
    }
}

fun main() = application {

    val trackingSim = TrackingSimulator("test.txt")
    trackingSim.runSimulation()


    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}




