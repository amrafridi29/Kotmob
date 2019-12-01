package com.kotmob.admob

import com.kotmob.admoblib.AdsApplication

class App : AdsApplication(){

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initInterstitial(getString(R.string.interstitial_ad_id))
    }
}