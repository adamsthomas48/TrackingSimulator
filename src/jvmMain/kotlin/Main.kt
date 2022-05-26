// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun ShipmentView(viewHelper: TrackerViewHelper, coroutineScope: CoroutineScope) {
    var updateHistory = remember { mutableStateListOf<TrackerViewHelper>() }

    Column{

        Text("Shipment: " + viewHelper.id)
        Text("Status: " + viewHelper.status)
        Text("Location: " + viewHelper.location)
        Text("Expected Delivery: " + viewHelper.deliveryDate.toString())

        Spacer(Modifier.size(5.dp))

        Text("Updates: ")
        Column{
            viewHelper.updates?.forEach { update ->
                Text(update.message)
            }
        }

        Spacer(Modifier.size(5.dp))
        Text("Notes: ")
        Column {
            viewHelper.notes?.forEach {note ->
                Text(note)
            }
        }

        Divider(color = Color.Blue, thickness = 2.dp)
        Spacer(Modifier.size(25.dp))

    }
}

@Composable
@Preview
fun App() {

    var text by remember { mutableStateOf("Hello, World!") }
    val viewHelpers = remember { mutableStateListOf<TrackerViewHelper>() }
    var searchId by remember { mutableStateOf("")}


    MaterialTheme {
        val coroutineScope = rememberCoroutineScope()
        Row(Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text("Tracking Simulation")
            }
            Column {
                TextField(value = searchId,
                onValueChange = {newText ->
                    searchId = newText
                })
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

                    viewHelpers.add(TrackerViewHelper(searchId))
                }) {
                    Text("Track Shipment")
                }
            }



        }
        Row(Modifier
            .padding(60.dp)
            .fillMaxWidth()){
            LazyColumn {
                items(viewHelpers) { viewHelper ->
                    ShipmentView(viewHelper, coroutineScope)
                }
            }
        }


    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}




