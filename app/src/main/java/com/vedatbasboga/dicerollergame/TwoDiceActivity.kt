package com.vedatbasboga.dicerollergame

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.vedatbasboga.dicerollergame.databinding.ActivityTwoDiceBinding

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


        // Add


        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

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

        val mediaPlayer = MediaPlayer.create(this , R.raw.dice_sound)

        val diceImage1Id = R.drawable.dice1
        val diceImage2Id = R.drawable.dice1

        layout.button.setOnClickListener {

            startZarAnimation(diceImage1Id)
            startZarAnimation(diceImage2Id)

            rollDice()

            mediaPlayer.start()

        }


    }


    private fun startZarAnimation (ImageId : Int) {

        val rotateAnimation = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.duration = 1000 // Animation time type second*1000
        layout.imageView2.setImageResource(ImageId)
        layout.imageView2.startAnimation(rotateAnimation)
        layout.imageView3.setImageResource(ImageId)
        layout.imageView3.startAnimation(rotateAnimation)

    }


    private class Dice(val num: Int) {

        fun roll(): Int {

            return (1..num).random()

        }
    }

    private fun rollDice() {

        val dice = Dice(6)
        val dice2 = Dice(6)
        val cubeRoller = dice.roll()
        val cubeRoller2 = dice2.roll()

        when(cubeRoller) {

            1 -> layout.imageView2.setImageResource(R.drawable.dice1)
            2 -> layout.imageView2.setImageResource(R.drawable.dice2)
            3 -> layout.imageView2.setImageResource(R.drawable.dice3)
            4 -> layout.imageView2.setImageResource(R.drawable.dice4)
            5 -> layout.imageView2.setImageResource(R.drawable.dice5)
            6 -> layout.imageView2.setImageResource(R.drawable.dice6)
        }

        when(cubeRoller2) {

            1 -> layout.imageView3.setImageResource(R.drawable.dice1)
            2 -> layout.imageView3.setImageResource(R.drawable.dice2)
            3 -> layout.imageView3.setImageResource(R.drawable.dice3)
            4 -> layout.imageView3.setImageResource(R.drawable.dice4)
            5 -> layout.imageView3.setImageResource(R.drawable.dice5)
            6 -> layout.imageView3.setImageResource(R.drawable.dice6)
        }

    }
}