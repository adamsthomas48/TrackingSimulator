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
    private val observers = mutableListOf<Observer>()



    override fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    private fun notifyObservers() {
        observers.forEach { it.notify(status)}
    }

    fun addNote(note: String){
        notes.add(note)
    }

    fun addUpdate(update: Update){
        updateHistory.add(update)
        notifyObservers()
    }



}