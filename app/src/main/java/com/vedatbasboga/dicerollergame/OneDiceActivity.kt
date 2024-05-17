package com.vedatbasboga.dicerollergame

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.vedatbasboga.dicerollergame.databinding.ActivityOneDiceBinding

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

        mAdView.adListener = object: AdListener() {
            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdImpression() {

            }

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        }


        val mediaPlayer = MediaPlayer.create(this, R.raw.dice_sound)

        val zarImageId = R.drawable.dice1

        layout.button2.setOnClickListener {

            startZarAnimation(zarImageId)

            rollDice()

            mediaPlayer.start()

        }

    }

    private fun startZarAnimation(ImageId: Int) {
        val rotateAnimation = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.duration = 1000 // Animation time type second*1000
        layout.imageView.setImageResource(ImageId)
        layout.imageView.startAnimation(rotateAnimation)



    }

    /* Görseli değiştiren animasyon
    private fun startZarAnimation2() {
        val rotateAnimation = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.duration = 1000 // Animasyon süresi milisaniye cinsindendir

        val handler = Handler()
        val diceImages = arrayOf(R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6)

        // Animasyon başladığında
        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                // Boş bırakabilirsiniz veya başka işlemler ekleyebilirsiniz
            }

            override fun onAnimationEnd(animation: Animation?) {
                // Animasyon bittiğinde
                val dice = Dice(6)
                layout.imageView.setImageResource(diceImages[dice.roll() - 1])
            }

            override fun onAnimationRepeat(animation: Animation?) {
                // Boş bırakabilirsiniz veya başka işlemler ekleyebilirsiniz
            }
        })

        // Animasyon sırasında
        val runnable = object : Runnable {
            var index = 0
            override fun run() {
                if (index < diceImages.size) {
                    layout.imageView.setImageResource(diceImages[index])
                    index++
                    handler.postDelayed(this, rotateAnimation.duration / diceImages.size)
                }
            }
        }

        handler.postDelayed(runnable, 0)
        layout.imageView.startAnimation(rotateAnimation)
    }

     */




    private class Dice(val num: Int) {

        fun roll(): Int {

            return (1..num).random()

        }
    }

    private fun rollDice() {

        val dice = Dice(6)

        when(dice.roll()) {

            1 -> {
                layout.imageView.setImageResource(R.drawable.dice1)
            }
            2 -> {
                layout.imageView.setImageResource(R.drawable.dice2)
            }
            3 -> {
                layout.imageView.setImageResource(R.drawable.dice3)
            }
            4 -> {
                layout.imageView.setImageResource(R.drawable.dice4)
            }
            5 -> {
                layout.imageView.setImageResource(R.drawable.dice5)
            }
            6 -> {
                layout.imageView.setImageResource(R.drawable.dice6)
            }
        }
    }
}