// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun ShipmentView(viewHelper: TrackerViewHelper, coroutineScope: CoroutineScope) {
    Row{
        Text(viewHelper.status)
    }
}

@Composable
@Preview
fun App() {

    var text by remember { mutableStateOf("Hello, World!") }
    val viewHelpers = remember { mutableStateListOf<TrackerViewHelper>() }


    MaterialTheme {
        val coroutineScope = rememberCoroutineScope()
        Row {
            Column {
                Text("Tracking Simulation")
            }
            Column {
                Button(onClick = {
                    coroutineScope.launch {
                        TrackingSimulator.runSimulation()
                    }

                }) {
                    Text("Run Simulation")
                }
            }
            Column {
                Button(onClick = {

                    viewHelpers.add(TrackerViewHelper("s10005"))
                }) {
                    Text("Track Shipment")
                }
            }

        }
        LazyColumn {
            items(viewHelpers) { viewHelper ->
                ShipmentView(viewHelper, coroutineScope)
            }
        }

    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}




