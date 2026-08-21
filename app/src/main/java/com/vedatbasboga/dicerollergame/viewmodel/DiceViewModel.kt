package com.vedatbasboga.dicerollergame.viewmodel

import androidx.lifecycle.ViewModel
import com.vedatbasboga.dicerollergame.model.Dice
import com.vedatbasboga.dicerollergame.model.RollRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class DiceUiState(
    val diceResults: List<Int> = listOf(1),
    val isRolling: Boolean = false,
    val history: List<RollRecord> = emptyList(),
    val totalRolls: Int = 0
)

class DiceViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DiceUiState())
    val uiState: StateFlow<DiceUiState> = _uiState.asStateFlow()

    private val dice = Dice()

    fun rollDice(count: Int = 1) {
        val results = List(count) { dice.roll() }
        _uiState.value = _uiState.value.copy(
            diceResults = results,
            isRolling = true
        )
    }

    fun onAnimationFinished() {
        val current = _uiState.value
        val record = RollRecord(results = current.diceResults)
        val updatedHistory = (listOf(record) + current.history).take(MAX_HISTORY)
        _uiState.value = current.copy(
            isRolling = false,
            history = updatedHistory,
            totalRolls = current.totalRolls + 1
        )
    }

    fun clearHistory() {
        _uiState.value = _uiState.value.copy(
            history = emptyList(),
            totalRolls = 0
        )
    }

    companion object {
        const val MAX_HISTORY = 20
    }
}
