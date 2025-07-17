package com.vedatbasboga.dicerollergame

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.vedatbasboga.dicerollergame.databinding.ActivityOneDiceBinding
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator

class OneDiceActivity : AppCompatActivity() {

    private lateinit var layout : ActivityOneDiceBinding

    private lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout = ActivityOneDiceBinding.inflate(layoutInflater)
        setContentView(layout.root)

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        layout.backButton.setOnClickListener {

            val intent = Intent(this@OneDiceActivity , MainActivity :: class.java)
            startActivity(intent)

        }

        val mediaPlayer = MediaPlayer.create(this, R.raw.dice_sound)


        val diceImages = arrayOf(
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6
        )
        layout.button2.setOnClickListener {
            val scaleX = ObjectAnimator.ofFloat(layout.button2, "scaleX", 1f, 1.2f, 1f)
            val scaleY = ObjectAnimator.ofFloat(layout.button2, "scaleY", 1f, 1.2f, 1f)
            val bounceSet = android.animation.AnimatorSet()
            bounceSet.playTogether(scaleX, scaleY)
            bounceSet.duration = 250
            bounceSet.start()

            startDiceSlotAnimation(diceImages) {
                mediaPlayer.start()
            }
        }

    }

    private class Dice(val num: Int) {

        fun roll(): Int {

            return (1..num).random()

        }
    }

    private fun startDiceSlotAnimation(diceImages: Array<Int>, onResult: () -> Unit) {
        val shake = ObjectAnimator.ofFloat(layout.imageView, "translationX", 0f, 25f, -25f, 15f, -15f, 6f, -6f, 0f)
        shake.duration = 400
        val rotate = ObjectAnimator.ofFloat(layout.imageView, "rotation", 0f, 360f)
        rotate.duration = 600
        val slotDuration = 700L
        val slotAnimator = ValueAnimator.ofInt(0, diceImages.size * 4)
        slotAnimator.duration = slotDuration
        slotAnimator.addUpdateListener { anim ->
            val idx = (anim.animatedValue as Int) % diceImages.size
            layout.imageView.setImageResource(diceImages[idx])
        }
        val animSet = android.animation.AnimatorSet()
        animSet.playTogether(shake, rotate, slotAnimator)
        animSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                // Gerçek zar sonucunu göster
                val dice = Dice(6)
                val result = dice.roll()
                layout.imageView.setImageResource(diceImages[result - 1])
                onResult()
            }
        })
        animSet.start()
    }
}