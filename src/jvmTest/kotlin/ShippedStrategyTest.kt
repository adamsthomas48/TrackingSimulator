import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ShippedStrategyTest {
    val shipment = Shipment("s10000", 123456)
    val strategy = ShippedStrategy(shipment)
    var instructions = mutableListOf<String>()

    @Test
    fun update() {
        instructions = mutableListOf<String>()
        instructions.add("shipped")
        instructions.add("s10001")
        instructions.add("12345")
        instructions.add("1111")

        strategy.update(instructions)
        assertEquals("shipped", strategy.newStatus)
        assertEquals(12345, strategy.timestamp)
        assertEquals(1111, strategy.deliveryDate)
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