import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class LostCanceledDeliveredStrategyTest {
    val shipment = Shipment("s10000", 123456)
    val strategy = LostCanceledDeliveredStrategy(shipment)
    var instructions = mutableListOf<String>()

    @Test
    fun update() {

        instructions = mutableListOf<String>()
        instructions.add("lost")
        instructions.add("s10001")
        instructions.add("12345")

        strategy.update(instructions)
        assertEquals("lost", strategy.newStatus)
        assertEquals(12345, strategy.timestamp)
    }

    @Test
    fun grabMessage() {
        assertEquals("Package was lost", strategy.grabMessage("lost"))
        assertEquals("Shipment canceled by customer", strategy.grabMessage("canceled"))
        assertEquals("Package was delivered!", strategy.grabMessage("delivered"))
        assertEquals("", strategy.grabMessage("alkasdjflk"))

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