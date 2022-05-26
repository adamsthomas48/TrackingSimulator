class LocationStrategy(var shipment: Shipment): UpdateStrategy {
    override fun update(instruction: List<String>) {
        val previousStatus = shipment.status
        val newStatus = instruction[0]
        val timestamp = instruction[2].toLong()
        val location = instruction[3]

        val message = "Package arrived in ${location}"
        shipment.status = newStatus
        shipment.currentLocation = location

        val update = Update(previousStatus, newStatus, timestamp, message)
        shipment.addUpdate(update)
    }
}