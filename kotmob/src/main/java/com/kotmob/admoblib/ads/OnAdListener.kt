package com.kotmob.admoblib.ads

sealed class OnAdListener{
    data class OnAdLoaded(private val nativeAd: NativeAd) : OnAdListener(){
        fun bindAdView(){
            nativeAd.populateUnifiedNativeAdView()
        }
    }
    data class OnAdFailedToLoad(val errorCode : Int, private val nativeAd: NativeAd) : OnAdListener(){
        fun loadAgain(){
            nativeAd.load()
        }
    }
}