class DelayedStrategy(var shipment: Shipment): UpdateStrategy {
    val previousStatus = shipment.status
    var newStatus: String = ""
    var timestamp: Long = 0

    val message = "Package has been delayed"
    override fun update(instruction: List<String>){
        newStatus = instruction[0]
        timestamp = instruction[2].toLong()

        shipment.status = newStatus
        shipment.expectedDeliveryDate = instruction[2].toLong()

        val update = Update(previousStatus, newStatus, timestamp, message)
        shipment.addUpdate(update)
    }

}