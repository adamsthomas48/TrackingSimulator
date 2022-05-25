class ShippedStrategy(var shipment: Shipment): UpdateStrategy {

    override fun update(instruction: List<String>) {
        val previousStatus = shipment.status
        val newStatus = instruction[0]
        val timestamp = instruction[2].toLong()
        val deliveryDate = instruction[3].toLong()

        shipment.status = newStatus
        shipment.expectedDeliveryDate = deliveryDate

        val update = Update(previousStatus, newStatus, timestamp)
        shipment.addUpdate(update)
    }
}