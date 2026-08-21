package com.vedatbasboga.dicerollergame.viewmodel

import androidx.lifecycle.ViewModel
import com.vedatbasboga.dicerollergame.model.Dice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class DiceUiState(
    val diceResults: List<Int> = listOf(1),
    val isRolling: Boolean = false
)

class DiceViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DiceUiState())
    val uiState: StateFlow<DiceUiState> = _uiState.asStateFlow()

    private val dice = Dice()

    fun rollDice(count: Int = 1) {
        _uiState.value = _uiState.value.copy(isRolling = true)
        val results = List(count) { dice.roll() }
        _uiState.value = DiceUiState(diceResults = results, isRolling = true)
    }

    fun onAnimationFinished() {
        _uiState.value = _uiState.value.copy(isRolling = false)
    }
}
