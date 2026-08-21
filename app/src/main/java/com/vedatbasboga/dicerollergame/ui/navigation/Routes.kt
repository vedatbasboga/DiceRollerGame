package com.vedatbasboga.dicerollergame.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object OneDice : Screen("one_dice")
    data object TwoDice : Screen("two_dice")
    data object History : Screen("history")
}
