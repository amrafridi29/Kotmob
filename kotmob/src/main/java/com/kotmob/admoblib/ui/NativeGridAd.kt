package com.kotmob.admoblib.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.kotmob.admoblib.R
import com.kotmob.admoblib.exceptions.NativeAdIdRequiredException
import kotlinx.android.synthetic.main.native_grid_ad.view.*


class NativeGridAd (context: Context, attrs: AttributeSet): NativeAdView(context, attrs){


   init {
       inflate(context ,R.layout.native_grid_ad ,  this)

       val attributes = context.obtainStyledAttributes(attrs , R.styleable.NativeGridAd , 0,0)
       val buttonShapeRes =attributes.getResourceId(R.styleable.NativeGridAd_button_shape , R.drawable.rounded_button_bg)
       val buttonTextColor = attributes.getColor(R.styleable.NativeGridAd_button_text_color,defaultButtonTextColor)
       val titleTextColor = attributes.getColor(R.styleable.NativeGridAd_headline_text_color,defaultTextColor)
       val bodyTextColor = attributes.getColor(R.styleable.NativeGridAd_body_text_color,defaultTextColor)
       val isBody = attributes.getBoolean(R.styleable.NativeGridAd_is_body, true)
       mNativeAdId = attributes.getString(R.styleable.NativeGridAd_native_ad_id) ?: throw NativeAdIdRequiredException()


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