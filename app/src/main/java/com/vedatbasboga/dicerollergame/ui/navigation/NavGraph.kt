package com.vedatbasboga.dicerollergame.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vedatbasboga.dicerollergame.ui.screen.HistoryScreen
import com.vedatbasboga.dicerollergame.ui.screen.HomeScreen
import com.vedatbasboga.dicerollergame.ui.screen.OneDiceScreen
import com.vedatbasboga.dicerollergame.ui.screen.TwoDiceScreen
import com.vedatbasboga.dicerollergame.viewmodel.DiceViewModel

@Composable
fun DiceRollerNavGraph(navController: NavHostController) {
    val diceViewModel: DiceViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToOneDice = { navController.navigate(Screen.OneDice.route) },
                onNavigateToTwoDice = { navController.navigate(Screen.TwoDice.route) },
                onNavigateToHistory = { navController.navigate(Screen.History.route) },
                historyCount = diceViewModel.uiState.value.totalRolls
            )
        }
        composable(Screen.OneDice.route) {
            OneDiceScreen(
                onNavigateBack = { navController.popBackStack() },
                viewModel = diceViewModel
            )
        }
        composable(Screen.TwoDice.route) {
            TwoDiceScreen(
                onNavigateBack = { navController.popBackStack() },
                viewModel = diceViewModel
            )
        }
        composable(Screen.History.route) {
            HistoryScreen(
                onNavigateBack = { navController.popBackStack() },
                viewModel = diceViewModel
            )
        }
    }
}
