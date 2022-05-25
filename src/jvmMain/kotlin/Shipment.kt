class Shipment(id: String, timestamp: Long): Observable {
    var status: String = "created"
    val id: String = id
    var notes = mutableListOf<String>()
        private set(value){
            field = value
        }
    var expectedDeliveryDate: Long = 0
    var currentLocation: String = "Factory"
    var updateHistory = mutableListOf<Update>()



    override fun addObserver(observer: Observer) {
        TODO("Not yet implemented")
    }

    override fun removeObserver(observer: Observer) {
        TODO("Not yet implemented")
    }

    fun addNote(note: String){
        notes.add(note)
    }

    fun addUpdate(update: Update){
        updateHistory.add(update)
    }



}