package com.kotmob.admob

import android.app.Application
import com.kotmob.admoblib.ads.KotMobInterstitial

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        KotMobInterstitial.initialize(this , getString(R.string.interstitial_ad_id))

    }
}
