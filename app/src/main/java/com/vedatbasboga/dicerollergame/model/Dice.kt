package com.vedatbasboga.dicerollergame.model

data class Dice(val sides: Int = 6) {
    fun roll(): Int = (1..sides).random()
}

data class RollRecord(
    val results: List<Int>,
    val total: Int = results.sum(),
    val timestamp: Long = System.currentTimeMillis()
)
