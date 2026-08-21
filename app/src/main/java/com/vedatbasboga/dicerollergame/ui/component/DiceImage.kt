package com.vedatbasboga.dicerollergame.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vedatbasboga.dicerollergame.R
import kotlinx.coroutines.launch

val diceDrawables = intArrayOf(
    R.drawable.dice1,
    R.drawable.dice2,
    R.drawable.dice3,
    R.drawable.dice4,
    R.drawable.dice5,
    R.drawable.dice6
)

@Composable
fun AnimatedDiceImage(
    result: Int,
    isRolling: Boolean,
    modifier: Modifier = Modifier,
    shakeDirection: Float = 1f,
    onAnimationEnd: () -> Unit = {}
) {
    val rotation = remember { Animatable(0f) }
    val offsetX = remember { Animatable(0f) }
    val scale = remember { Animatable(1f) }

    LaunchedEffect(isRolling) {
        if (isRolling) {
            launch {
                rotation.animateTo(
                    targetValue = 360f,
                    animationSpec = tween(durationMillis = 600, easing = LinearEasing)
                )
                rotation.snapTo(0f)
            }
            launch {
                // Shake animation
                val shakeValues = listOf(25f, -25f, 15f, -15f, 6f, -6f, 0f)
                for (value in shakeValues) {
                    offsetX.animateTo(
                        targetValue = value * shakeDirection,
                        animationSpec = tween(durationMillis = 57)
                    )
                }
            }
            launch {
                scale.animateTo(
                    targetValue = 1.15f,
                    animationSpec = tween(durationMillis = 300)
                )
                scale.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                onAnimationEnd()
            }
        }
    }

    Image(
        painter = painterResource(id = diceDrawables[result - 1]),
        contentDescription = "Dice showing $result",
        modifier = modifier
            .size(150.dp)
            .graphicsLayer {
                rotationZ = rotation.value
                translationX = offsetX.value
                scaleX = scale.value
                scaleY = scale.value
            }
    )
}
