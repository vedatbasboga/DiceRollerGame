package com.vedatbasboga.dicerollergame.model

data class Dice(val sides: Int = 6) {
    fun roll(): Int = (1..sides).random()
}
