class LostCanceledDeliveredStrategy(var shipment: Shipment): UpdateStrategy {
    override fun update(instruction: List<String>) {
        val previousStatus = shipment.status
        val newStatus = instruction[0]
        val timestamp = instruction[2].toLong()

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