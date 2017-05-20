package io.github.ordonteam.ogame.script

import io.github.ordonteam.ogame.script.core.getTradeRequests
import org.junit.Assert
import org.junit.Test

class GetTradeResultTest {

    @Test
    fun shouldTradeMetalToCrystal() {
        Assert.assertEquals(1_000_000, getTradeRequests(7_000_000, 3_000_000, 1_000_000).first.first)
        Assert.assertEquals(1_000_000, getTradeRequests(12_000_000, 7_000_000, 2_000_000).first.first)
    }

    @Test
    fun shouldTradeMetalToDeuterium() {
        Assert.assertEquals(1_000_000, getTradeRequests(9_000_000, 4_000_000, 0).first.second)
        Assert.assertEquals(1_000_000, getTradeRequests(14_000_000, 8_000_000, 1_000_000).first.second)
        Assert.assertEquals(2, getTradeRequests(5_000_008, 4_000_000, 999_999).first.second)
    }

    @Test
    fun shouldTradeCrystalToMetal() {
        Assert.assertEquals(2_000_000, getTradeRequests(3_000_000, 5_000_000, 1_000_000).second.first)
        Assert.assertEquals(2_000_000, getTradeRequests(8_000_000, 9_000_000, 2_000_000).second.first)
    }

    @Test
    fun shouldTradeCrystalToDeuterium() {
        Assert.assertEquals(1_000_000, getTradeRequests(5_000_000, 6_000_000, 0).second.second)
        Assert.assertEquals(1_000_000, getTradeRequests(10_000_000, 10_000_000, 1_000_000).second.second)
    }

    @Test
    fun shouldTradeDeuteriumToMetal() {
        Assert.assertEquals(4_000_000, getTradeRequests(1_000_000, 4_000_000, 2_000_000).third.first)
        Assert.assertEquals(4_000_000, getTradeRequests(6_000_000, 8_000_000, 3_000_000).third.first)
    }

    @Test
    fun shouldTradeDeuteriumToCrystal() {
        Assert.assertEquals(2_000_000, getTradeRequests(5_000_000, 2_000_000, 2_000_000).third.second)
        Assert.assertEquals(2_000_000, getTradeRequests(10_000_000, 6_000_000, 3_000_000).third.second)
    }

    @Test
    fun shouldNotTradeAnything() {
        Assert.assertEquals(0, getTradeRequests(8_000_000, 4_000_000, 1_000_000).first.first)
        Assert.assertEquals(0, getTradeRequests(8_000_000, 4_000_000, 1_000_000).first.second)
        Assert.assertEquals(0, getTradeRequests(8_000_000, 4_000_000, 1_000_000).second.first)
        Assert.assertEquals(0, getTradeRequests(8_000_000, 4_000_000, 1_000_000).second.second)
        Assert.assertEquals(0, getTradeRequests(8_000_000, 4_000_000, 1_000_000).third.first)
        Assert.assertEquals(0, getTradeRequests(8_000_000, 4_000_000, 1_000_000).third.second)
    }

}
