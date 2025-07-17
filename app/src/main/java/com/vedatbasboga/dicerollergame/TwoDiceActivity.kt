package com.vedatbasboga.dicerollergame

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.vedatbasboga.dicerollergame.databinding.ActivityTwoDiceBinding
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator

class TwoDiceActivity : AppCompatActivity() {

    private lateinit var layout: ActivityTwoDiceBinding
    private lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout = ActivityTwoDiceBinding.inflate(layoutInflater)
        setContentView(layout.root)

        layout.backButton2.setOnClickListener {
            val intent = Intent(this@TwoDiceActivity , MainActivity :: class.java)
            startActivity(intent)
        }

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val mediaPlayer = MediaPlayer.create(this , R.raw.dice_sound)
        val diceImages = arrayOf(
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6
        )
        layout.button.setOnClickListener {
            val scaleX = ObjectAnimator.ofFloat(layout.button, "scaleX", 1f, 1.2f, 1f)
            val scaleY = ObjectAnimator.ofFloat(layout.button, "scaleY", 1f, 1.2f, 1f)
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
        val shake2 = ObjectAnimator.ofFloat(layout.imageView2, "translationX", 0f, 25f, -25f, 15f, -15f, 6f, -6f, 0f)
        shake2.duration = 400
        val rotate2 = ObjectAnimator.ofFloat(layout.imageView2, "rotation", 0f, 360f)
        rotate2.duration = 600
        val shake3 = ObjectAnimator.ofFloat(layout.imageView3, "translationX", 0f, -25f, 25f, -15f, 15f, -6f, 6f, 0f)
        shake3.duration = 400
        val rotate3 = ObjectAnimator.ofFloat(layout.imageView3, "rotation", 0f, 360f)
        rotate3.duration = 600
        val slotDuration = 700L
        val slotAnimator2 = ValueAnimator.ofInt(0, diceImages.size * 4)
        slotAnimator2.duration = slotDuration
        slotAnimator2.addUpdateListener { anim ->
            val idx = (anim.animatedValue as Int) % diceImages.size
            layout.imageView2.setImageResource(diceImages[idx])
        }
        val slotAnimator3 = ValueAnimator.ofInt(0, diceImages.size * 4)
        slotAnimator3.duration = slotDuration
        slotAnimator3.addUpdateListener { anim ->
            val idx = (anim.animatedValue as Int) % diceImages.size
            layout.imageView3.setImageResource(diceImages[idx])
        }
        val animSet = android.animation.AnimatorSet()
        animSet.playTogether(shake2, rotate2, slotAnimator2, shake3, rotate3, slotAnimator3)
        animSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                val dice = Dice(6)
                val dice2 = Dice(6)
                val result1 = dice.roll()
                val result2 = dice2.roll()
                layout.imageView2.setImageResource(diceImages[result1 - 1])
                layout.imageView3.setImageResource(diceImages[result2 - 1])
                onResult()
            }
        })
        animSet.start()
    }
}