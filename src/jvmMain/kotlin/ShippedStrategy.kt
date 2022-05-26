class ShippedStrategy(var shipment: Shipment): UpdateStrategy {
    val previousStatus = shipment.status
    var newStatus: String = ""
    var timestamp: Long = 0
    var deliveryDate: Long = 0
    override fun update(instruction: List<String>) {
        newStatus = instruction[0]
        timestamp = instruction[2].toLong()
        deliveryDate = instruction[3].toLong()
        val message = "Packaged has been shipped"

        shipment.status = newStatus
        shipment.expectedDeliveryDate = deliveryDate

        val update = Update(previousStatus, newStatus, timestamp, message)
        shipment.addUpdate(update)
    }
}