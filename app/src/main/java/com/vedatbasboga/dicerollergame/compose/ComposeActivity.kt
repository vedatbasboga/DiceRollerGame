package com.vedatbasboga.dicerollergame.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.vedatbasboga.dicerollergame.OneDiceActivity
import com.vedatbasboga.dicerollergame.R
import com.vedatbasboga.dicerollergame.TwoDiceActivity
import com.vedatbasboga.dicerollergame.compose.components.ComposeActivityScreen
import com.vedatbasboga.dicerollergame.databinding.ActivityComposeBinding

class ComposeActivity : AppCompatActivity() {

    private lateinit var layout : ActivityComposeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout = ActivityComposeBinding.inflate(layoutInflater)
        setContentView(layout.root)


        layout.composeView.setContent {
            ComposeActivityScreen(
                onNavigateToOneDiceActivity = {
                    startActivity(Intent(this@ComposeActivity, OneDiceActivity::class.java))
                },
                onNavigateToTwoDiceActivity = {
                    startActivity(Intent(this@ComposeActivity, TwoDiceActivity::class.java))
                }
            )
        }
    }
}