import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class TrackerViewHelper(val shipmentId: String): Observer {
    val shipment = TrackingSimulator.findShipment(shipmentId)
    var status by mutableStateOf(shipment?.status ?: "No Shipment Found")


    init {
        shipment?.addObserver(this)
        println("TrackerView Created")
        println(shipment?.status ?: "Null")
    }
    override fun notify(status: String) {
        this.status = shipment?.status ?: "Null"
    }


}