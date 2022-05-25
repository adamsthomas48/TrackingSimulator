import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

object TrackingSimulator {

    var shipments = mutableListOf<Shipment>()

    fun findShipment(id: String): Shipment? {
        return shipments.find {it.id == id}
    }


    fun addShipment(shipment: List<String>){
        val newShipment = Shipment(shipment[1], shipment[2].toLong())
        shipments.add(newShipment)
    }

    suspend fun runSimulation() = coroutineScope {

        launch {
            val instructions = parseShipments("test.txt")

            instructions.forEach(){
                println(it[0])

                if(it[0] == "created"){
                    addShipment(it)
                }
                else{
                    val updateStrategies = mapOf<String, UpdateStrategy>(
                        Pair("delayed", DelayedStrategy(findShipment(it[1])!!)),
                        Pair("shipped", ShippedStrategy(findShipment(it[1])!!)),
                        Pair("location", LocationStrategy(findShipment(it[1])!!)),
                        Pair("noteadded", NoteAddedStrategy(findShipment(it[1])!!)),
                        Pair("lost", LostCanceledDeliveredStrategy(findShipment(it[1])!!)),
                        Pair("canceled", LostCanceledDeliveredStrategy(findShipment(it[1])!!)),
                        Pair("delivered", LostCanceledDeliveredStrategy(findShipment(it[1])!!)),

                        )


                    updateStrategies[it[0]]?.update(it)
                }
                for(shipment in shipments){
                    println(shipment.id + " " + shipment.status)
                }
                println("============================")
                //Thread.sleep(1_000)
                delay(1000)
        }





       }
        //println("Testing")


    }

    fun parseShipments(filename: String): List<List<String>> {
        val instructions = mutableListOf<List<String>>()

        File(filename).forEachLine {
            val shipment = it.split(",").toList()
            instructions.add(shipment)
        }
        return instructions
    }



}