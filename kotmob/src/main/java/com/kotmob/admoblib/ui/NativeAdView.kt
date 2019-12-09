package com.kotmob.admoblib.ui

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.google.android.gms.ads.formats.UnifiedNativeAdView

abstract class NativeAdView (context : Context, attrs : AttributeSet) : LinearLayout(context, attrs){
    protected var defaultTextColor = Color.BLACK
    protected var defaultButtonTextColor = Color.WHITE
    protected lateinit var mNativeAdId : String
    var progressBar : ProgressBar?=null
    var unifiedNativeAdView : UnifiedNativeAdView?= null

    val nativeAdId
        get() = mNativeAdId

     abstract fun hideAd()
}