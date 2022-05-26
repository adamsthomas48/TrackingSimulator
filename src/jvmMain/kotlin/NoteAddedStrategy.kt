class NoteAddedStrategy(var shipment: Shipment): UpdateStrategy {
    val previousStatus = shipment.status
    var newStatus: String = ""
    var timestamp: Long = 0
    var note: String = ""
    override fun update(instruction: List<String>) {
        newStatus = instruction[0]
        timestamp = instruction[2].toLong()
        note = instruction[3]

        val message = "Notes Added"
        shipment.status = newStatus
        shipment.addNote(note)

        val update = Update(previousStatus, newStatus, timestamp, message)
        shipment.addUpdate(update)
    }
}