package com.vedatbasboga.dicerollergame.viewmodel

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DiceViewModelTest {

    private lateinit var viewModel: DiceViewModel

    @Before
    fun setup() {
        viewModel = DiceViewModel()
    }

    @Test
    fun `initial state has default values`() {
        val state = viewModel.uiState.value
        assertEquals(listOf(1), state.diceResults)
        assertFalse(state.isRolling)
        assertTrue(state.history.isEmpty())
        assertEquals(0, state.totalRolls)
    }

    @Test
    fun `rollDice sets isRolling to true`() {
        viewModel.rollDice(1)
        assertTrue(viewModel.uiState.value.isRolling)
    }

    @Test
    fun `rollDice generates correct number of results`() {
        viewModel.rollDice(2)
        assertEquals(2, viewModel.uiState.value.diceResults.size)
    }

    @Test
    fun `rollDice results are within valid range`() {
        viewModel.rollDice(2)
        viewModel.uiState.value.diceResults.forEach { result ->
            assertTrue("Result $result should be between 1 and 6", result in 1..6)
        }
    }

    @Test
    fun `onAnimationFinished sets isRolling to false`() {
        viewModel.rollDice(1)
        viewModel.onAnimationFinished()
        assertFalse(viewModel.uiState.value.isRolling)
    }

    @Test
    fun `onAnimationFinished adds to history`() {
        viewModel.rollDice(1)
        viewModel.onAnimationFinished()
        assertEquals(1, viewModel.uiState.value.history.size)
        assertEquals(1, viewModel.uiState.value.totalRolls)
    }

    @Test
    fun `multiple rolls accumulate in history`() {
        repeat(5) {
            viewModel.rollDice(1)
            viewModel.onAnimationFinished()
        }
        assertEquals(5, viewModel.uiState.value.history.size)
        assertEquals(5, viewModel.uiState.value.totalRolls)
    }

    @Test
    fun `history is limited to MAX_HISTORY entries`() {
        repeat(25) {
            viewModel.rollDice(1)
            viewModel.onAnimationFinished()
        }
        assertEquals(DiceViewModel.MAX_HISTORY, viewModel.uiState.value.history.size)
        assertEquals(25, viewModel.uiState.value.totalRolls)
    }

    @Test
    fun `clearHistory resets history and totalRolls`() {
        repeat(3) {
            viewModel.rollDice(1)
            viewModel.onAnimationFinished()
        }
        viewModel.clearHistory()
        assertTrue(viewModel.uiState.value.history.isEmpty())
        assertEquals(0, viewModel.uiState.value.totalRolls)
    }

    @Test
    fun `history records preserve dice results`() {
        viewModel.rollDice(2)
        val results = viewModel.uiState.value.diceResults
        viewModel.onAnimationFinished()
        assertEquals(results, viewModel.uiState.value.history[0].results)
    }

    @Test
    fun `latest roll appears first in history`() {
        viewModel.rollDice(1)
        viewModel.onAnimationFinished()
        val firstRoll = viewModel.uiState.value.history[0]

        viewModel.rollDice(1)
        viewModel.onAnimationFinished()
        val secondRoll = viewModel.uiState.value.history[0]

        assertTrue(secondRoll.timestamp >= firstRoll.timestamp)
    }
}
