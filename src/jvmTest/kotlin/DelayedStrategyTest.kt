
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

internal class DelayedStrategyTest {

    @Test
    fun update() {
        val shipment = Shipment("s10000", 123456)
        val delayedStrategy = DelayedStrategy(shipment)
        val instructions = mutableListOf<String>()
        instructions.add("delayed")
        instructions.add("s10001")
        instructions.add("12345")
        instructions.add("123456")

        delayedStrategy.update(instructions)
        assertEquals("delayed", delayedStrategy.newStatus)
        assertEquals(12345, delayedStrategy.timestamp)


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