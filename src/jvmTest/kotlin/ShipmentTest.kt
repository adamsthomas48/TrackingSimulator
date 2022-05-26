import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ShipmentTest {
    val shipment = Shipment("s10000", 123456)
    val strategy = ShippedStrategy(shipment)
    var instructions = mutableListOf<String>()
    init {
        instructions = mutableListOf<String>()
        instructions.add("shipped")
        instructions.add("s10000")
        instructions.add("12345")
        instructions.add("1111")

        strategy.update(instructions)
    }

    @Test
    fun getStatus() {
        assertEquals("shipped", shipment.status)
    }

    @Test
    fun setStatus() {
        shipment.status = "testing"
        assertEquals("testing", shipment.status)
    }

    @Test
    fun getId() {
        assertEquals("s10000", shipment.id)
    }

    @Test
    fun getNotes() {
        val notes = mutableListOf<String>()
        notes.add("Hello World")
        shipment.addNote("Hello World")

        assertEquals(notes, shipment.notes)
    }

    @Test
    fun setNotes() {
        val notes = mutableListOf<String>()
        notes.add("Hello World")
        notes.add("Test")
        shipment.addNote("Hello World")
        shipment.notes = notes

        assertEquals(notes, shipment.notes)
    }

    @Test
    fun getExpectedDeliveryDate() {
        assertEquals(1111, shipment.expectedDeliveryDate)
    }

    @Test
    fun setExpectedDeliveryDate() {
        shipment.expectedDeliveryDate = 1234
        assertEquals(1234, shipment.expectedDeliveryDate)
    }

    @Test
    fun getCurrentLocation() {
        assertEquals("Factory", shipment.currentLocation)
    }

    @Test
    fun setCurrentLocation() {
        shipment.currentLocation = "Texas"
        assertEquals("Texas", shipment.currentLocation)
    }

    @Test
    fun getUpdateHistory() {
        val updates = mutableListOf<Update>()
        val update = Update("created", "shipped", 1234, "shipped")
        updates.add(update)
        shipment.updateHistory = updates
        assertEquals(updates, shipment.updateHistory)
    }

    @Test
    fun setUpdateHistory() {
        val updates = mutableListOf<Update>()
        val update = Update("created", "shipped", 1234, "shipped")
        updates.add(update)
        shipment.updateHistory = updates
        assertEquals(updates, shipment.updateHistory)
    }

    @Test
    fun addObserver() {
        val observers = mutableListOf<Observer>()
        val observer = TrackerViewHelper("s10000")
        observers.add(observer)
        shipment.addObserver(observer)
    }

    @Test
    fun removeObserver() {
        val observers = mutableListOf<Observer>()
        val observer = TrackerViewHelper("s10000")
        observers.add(observer)
        shipment.addObserver(observer)
        shipment.removeObserver(observer)
    }

    @Test
    fun addNote() {
        val notes = mutableListOf<String>()
        notes.add("Hello World")
        notes.add("Test")
        shipment.addNote("Hello World")
        shipment.notes = notes

        assertEquals(notes, shipment.notes)

    }

    @Test
    fun addUpdate() {
        val updates = mutableListOf<Update>()
        val update = Update("created", "shipped", 1234, "shipped")
        updates.add(update)
        shipment.updateHistory = updates
        assertEquals(updates, shipment.updateHistory)
    }
}