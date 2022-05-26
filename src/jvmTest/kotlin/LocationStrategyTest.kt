import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class LocationStrategyTest {

    @Test
    fun update(){
        val shipment = Shipment("s10000", 123456)
        val locationStrategy = LocationStrategy(shipment)
        val instructions = mutableListOf<String>()
        instructions.add("location")
        instructions.add("s10001")
        instructions.add("12345")
        instructions.add("Los Angeles CA")

        locationStrategy.update(instructions)
        assertEquals("location", locationStrategy.newStatus)
        assertEquals(12345, locationStrategy.timestamp)
        assertEquals("Los Angeles CA", locationStrategy.location)
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