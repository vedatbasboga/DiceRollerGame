package com.vedatbasboga.dicerollergame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.vedatbasboga.dicerollergame.ui.navigation.DiceRollerNavGraph
import com.vedatbasboga.dicerollergame.ui.theme.DiceRollerGameTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerGameTheme {
                val navController = rememberNavController()
                DiceRollerNavGraph(navController = navController)
            }
        }
    }
}
