package com.vedatbasboga.dicerollergame.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DiceTest {

    @Test
    fun `dice roll returns value within range`() {
        val dice = Dice(6)
        repeat(100) {
            val result = dice.roll()
            assertTrue("Result $result should be between 1 and 6", result in 1..6)
        }
    }

    @Test
    fun `dice with custom sides returns value within range`() {
        val dice = Dice(20)
        repeat(100) {
            val result = dice.roll()
            assertTrue("Result $result should be between 1 and 20", result in 1..20)
        }
    }

    @Test
    fun `dice default sides is 6`() {
        val dice = Dice()
        assertEquals(6, dice.sides)
    }

    @Test
    fun `roll record calculates total correctly`() {
        val record = RollRecord(results = listOf(3, 5))
        assertEquals(8, record.total)
    }

    @Test
    fun `roll record single dice total equals result`() {
        val record = RollRecord(results = listOf(4))
        assertEquals(4, record.total)
    }
}
