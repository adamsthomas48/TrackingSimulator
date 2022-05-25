class LostCanceledDeliveredStrategy(var shipment: Shipment): UpdateStrategy {
    override fun update(instruction: List<String>) {
        val previousStatus = shipment.status
        val newStatus = instruction[0]
        val timestamp = instruction[2].toLong()

        shipment.status = newStatus
        val update = Update(previousStatus, newStatus, timestamp)
        shipment.addUpdate(update)
    }
}