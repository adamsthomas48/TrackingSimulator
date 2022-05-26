class LocationStrategy(var shipment: Shipment): UpdateStrategy {
    val previousStatus = shipment.status
    var newStatus: String = ""
    var timestamp: Long = 0
    var location: String = ""

    override fun update(instruction: List<String>) {

        newStatus = instruction[0]
        timestamp = instruction[2].toLong()
        location = instruction[3]

        val message = "Package arrived in ${location}"
        shipment.status = newStatus
        shipment.currentLocation = location

        val update = Update(previousStatus, newStatus, timestamp, message)
        shipment.addUpdate(update)
    }
}