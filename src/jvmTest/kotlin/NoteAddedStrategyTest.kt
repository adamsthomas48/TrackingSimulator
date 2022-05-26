import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class NoteAddedStrategyTest {
    val shipment = Shipment("s10000", 123456)
    val strategy = NoteAddedStrategy(shipment)
    var instructions = mutableListOf<String>()

    @Test
    fun update() {
        instructions = mutableListOf<String>()
        instructions.add("noteadded")
        instructions.add("s10001")
        instructions.add("12345")
        instructions.add("This is a test")

        strategy.update(instructions)
        assertEquals("noteadded", strategy.newStatus)
        assertEquals(12345, strategy.timestamp)
        assertEquals("This is a test", strategy.note)
    }

    @Test
    fun getShipment() {
        val shipment = Shipment("s10000", 123456)
        val delayedStrategy = DelayedStrategy(shipment)
        assertEquals(delayedStrategy.shipment, shipment)

        val shipment2 = Shipment("s10000", 123456)
        val delayedStrategy2 = DelayedStrategy(shipment2)
        assertEquals(delayedStrategy2.shipment, shipment2)
    }

    @Test
    fun setShipment() {
        val shipment = Shipment("s10000", 123456)
        val delayedStrategy = DelayedStrategy(shipment)

        val shipment2 = Shipment("s10000", 123456)
        delayedStrategy.shipment = shipment2

        assertEquals(delayedStrategy.shipment, shipment2)
    }
}