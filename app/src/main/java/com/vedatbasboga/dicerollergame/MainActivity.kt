package com.vedatbasboga.dicerollergame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.vedatbasboga.dicerollergame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var layout: ActivityMainBinding

    private lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout = ActivityMainBinding.inflate(layoutInflater)
        setContentView(layout.root)

        MobileAds.initialize(this) {}

        mAdView = layout.adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        layout.oneDice.setOnClickListener {

            val intent = Intent(this@MainActivity , OneDiceActivity ::class.java)
            startActivity(intent)

        }

        layout.twoDice.setOnClickListener {

            val intent = Intent(this@MainActivity , TwoDiceActivity :: class.java)
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
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        }

    }
}