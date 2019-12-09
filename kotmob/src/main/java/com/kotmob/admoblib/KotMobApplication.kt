package com.kotmob.admoblib

import android.app.Application
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

abstract class KotMobApplication : Application(){

    private var mInterstitialAd: InterstitialAd? = null
    private  var onAdListener : ((()-> Unit))? = null

    protected fun initInterstitial(adId : String) {
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd?.adUnitId = adId

        mInterstitialAd?.adListener = object : AdListener() {
            override fun onAdFailedToLoad(i: Int) {
                onAdListener?.invoke()
            }
            override fun onAdClosed() {
                super.onAdClosed()
                loadInterstitial()
                onAdListener?.invoke()
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

    fun showInterstitial(listener : (()-> Unit)?) {
        this.onAdListener = listener
        mInterstitialAd?.let {
            if(it.isLoaded){
                it.show()
            }else{
                //loadInterstitial()
                onAdListener?.invoke()
            }
        } ?: onAdListener?.invoke()
    }
}