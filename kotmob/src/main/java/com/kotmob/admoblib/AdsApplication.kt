package com.kotmob.admoblib

import android.app.Application
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

abstract class AdsApplication : Application(){

    private var mInterstitialAd: InterstitialAd? = null
    private lateinit var onAdListener : ()-> Unit?

    protected fun initInterstitial(adId : String) {
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd?.adUnitId = adId

        mInterstitialAd?.adListener = object : AdListener() {
            override fun onAdFailedToLoad(i: Int) {
                onAdListener()
            }
            override fun onAdClosed() {
                super.onAdClosed()
                loadInterstitial()
                onAdListener()
            }
        }

        loadInterstitial()
    }

    private fun loadInterstitial() {
        mInterstitialAd?.let {
            if (!it.isLoading) {
                it.loadAd(
                    AdRequest.Builder()
                        .build()
                )
            }
        }
    }

    fun showInterstitial(listener : ()-> Unit) {
        this.onAdListener = listener
        mInterstitialAd?.let {
            if(it.isLoaded){
                it.show()
            }
        } ?: onAdListener()
    }
}