package com.kotmob.admoblib.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.kotmob.admoblib.R
import com.kotmob.admoblib.exceptions.NativeAdIdRequiredException
import kotlinx.android.synthetic.main.native_banner_ad.view.ad_body
import kotlinx.android.synthetic.main.native_banner_ad.view.ad_call_to_action
import kotlinx.android.synthetic.main.native_banner_ad.view.ad_headline
import kotlinx.android.synthetic.main.native_banner_ad.view.progress_bar
import kotlinx.android.synthetic.main.native_banner_ad.view.uniform


class NativeBannerAd (context: Context, attrs: AttributeSet): NativeAdView(context, attrs){


   init {
       inflate(context ,R.layout.native_banner_ad ,  this)
       val attributes = context.obtainStyledAttributes(attrs , R.styleable.NativeBannerAd , 0,0)
       val buttonShapeRes =attributes.getResourceId(R.styleable.NativeBannerAd_button_shape , R.drawable.rounded_button_bg)
       val buttonTextColor = attributes.getColor(R.styleable.NativeBannerAd_button_text_color,defaultButtonTextColor)
       val titleTextColor = attributes.getColor(R.styleable.NativeBannerAd_headline_text_color,defaultTextColor)
       val bodyTextColor = attributes.getColor(R.styleable.NativeBannerAd_body_text_color,defaultTextColor)
       val isBody = attributes.getBoolean(R.styleable.NativeBannerAd_is_body, true)
       mNativeAdId = attributes.getString(R.styleable.NativeBannerAd_native_ad_id) ?: throw NativeAdIdRequiredException()


       ad_call_to_action.setBackgroundResource(buttonShapeRes)
       ad_call_to_action.setTextColor(buttonTextColor)

       ad_headline.setTextColor(titleTextColor)

        ad_body.visibility = when(isBody){
            true -> View.VISIBLE
            false -> View.GONE
        }
       ad_body.setTextColor(bodyTextColor)

       progressBar =progress_bar
       unifiedNativeAdView = uniform
       attributes.recycle()
   }
}