package com.kotmob.admoblib.ui

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.kotmob.admoblib.R
import com.kotmob.admoblib.exceptions.BannerAdIdRequiredException
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import kotlinx.android.synthetic.main.banner_ad.view.*

class BannerAd(context : Context , attrs : AttributeSet) : FrameLayout(context, attrs){

    init {
        inflate(context, R.layout.banner_ad , this)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.BannerAd , 0, 0)
        val  bannerAdId = attributes.getString(R.styleable.BannerAd_banner_ad_id) ?: throw BannerAdIdRequiredException()
        loadAd(banner_ad_layout , bannerAdId)
        attributes.recycle()
    }


    fun loadAd(adContainer: FrameLayout? , adId : String) {
        if (adContainer == null) return
        val adView = AdView(context)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = adId

        val adRequest = AdRequest.Builder()
            // .addTestDevice("51D5AFA37D1312927451D193CAFAD12F")
            .build()
        adView.loadAd(adRequest)
        adView.adListener = object : AdListener() {
            override fun onAdFailedToLoad(i: Int) {
                Log.e("Utils", "Banner Ad loading failed error code = $i")
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                val height = AdSize.BANNER.getHeightInPixels(context)
                animateHeight(adContainer, 500, height)
            }
        }
        val params =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        adContainer.addView(adView, params)
    }

    private fun animateHeight(v: View, duration: Int, targetHeight: Int) {
        val prevHeight = v.height
        val valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight)
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.addUpdateListener { animation ->
            v.layoutParams.height = animation.animatedValue as Int
            v.requestLayout()
        }
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.duration = duration.toLong()
        valueAnimator.start()
    }
}