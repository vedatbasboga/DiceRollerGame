package com.vedatbasboga.dicerollergame.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vedatbasboga.dicerollergame.ui.screen.HomeScreen
import com.vedatbasboga.dicerollergame.ui.screen.OneDiceScreen
import com.vedatbasboga.dicerollergame.ui.screen.TwoDiceScreen

@Composable
fun DiceRollerNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToOneDice = { navController.navigate(Screen.OneDice.route) },
                onNavigateToTwoDice = { navController.navigate(Screen.TwoDice.route) }
            )
        }
        composable(Screen.OneDice.route) {
            OneDiceScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.TwoDice.route) {
            TwoDiceScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
