class LostCanceledDeliveredStrategy(var shipment: Shipment): UpdateStrategy {
    val previousStatus = shipment.status
    var newStatus: String = ""
    var timestamp: Long = 0
    override fun update(instruction: List<String>) {

        newStatus = instruction[0]
        timestamp = instruction[2].toLong()

        val message = grabMessage(newStatus)

        shipment.status = newStatus
        val update = Update(previousStatus, newStatus, timestamp, message)
        shipment.addUpdate(update)
    }

    fun grabMessage(newStatus: String): String {
        var message: String
        if(newStatus == "lost"){
            message = "Package was lost"
        }
        else if(newStatus == "canceled"){
            message = "Shipment canceled by customer"
        }
        else if(newStatus == "delivered"){
            message = "Package was delivered!"
        }
        else {
            message = ""
        }
        return message
    }
}