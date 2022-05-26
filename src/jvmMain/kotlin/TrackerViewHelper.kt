import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class TrackerViewHelper(val shipmentId: String): Observer {
    val shipment = TrackingSimulator.findShipment(shipmentId)
    var status by mutableStateOf(shipment?.status ?: "No Shipment Found")
    var id by mutableStateOf(shipment?.id ?: "")
    var location by mutableStateOf(shipment?.currentLocation ?: "")
    var deliveryDate by mutableStateOf(shipment?.expectedDeliveryDate ?: 0)
    var updates by mutableStateOf(shipment?.updateHistory)
    var notes by mutableStateOf(shipment?.notes)


    init {
        shipment?.addObserver(this)
    }
    override fun notify(ship: Shipment) {
        this.status = shipment?.status ?: "Null"
        this.location = shipment?.currentLocation ?: ""
        this.deliveryDate = shipment?.expectedDeliveryDate ?: 0
        this.updates = shipment?.updateHistory
        this.notes = shipment?.notes

    }

    fun removeShipment(){
        shipment?.removeObserver(this)
    }


}