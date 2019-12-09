package com.kotmob.admoblib.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.kotmob.admoblib.R
import com.kotmob.admoblib.exceptions.NativeAdIdRequiredException
import com.kotmob.admoblib.models.NativeAdSize
import kotlinx.android.synthetic.main.native_banner_opt_ad.view.*


class NativeBannerOptAd (context: Context, attrs: AttributeSet): NativeAdView(context, attrs){



   init {
       inflate(context ,R.layout.native_banner_opt_ad ,  this)
       val attributes = context.obtainStyledAttributes(attrs , R.styleable.NativeBannerOptAd , 0,0)
       val buttonShapeRes =attributes.getResourceId(R.styleable.NativeBannerOptAd_button_shape , R.drawable.rounded_button_bg)
       val buttonTextColor = attributes.getColor(R.styleable.NativeBannerOptAd_button_text_color,defaultButtonTextColor)
       val titleTextColor = attributes.getColor(R.styleable.NativeBannerOptAd_headline_text_color,defaultTextColor)
       val bodyTextColor = attributes.getColor(R.styleable.NativeBannerOptAd_body_text_color,defaultTextColor)
       val isBody = attributes.getBoolean(R.styleable.NativeBannerOptAd_is_body, true)
       mNativeAdId = attributes.getString(R.styleable.NativeBannerOptAd_native_ad_id) ?: throw NativeAdIdRequiredException()

       val adSize = NativeAdSize.values()[attributes.getInt(R.styleable.NativeBannerOptAd_ad_size,0)];
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

       val height = when(adSize){
           NativeAdSize.MEDIUM-> resources.getDimensionPixelSize(R.dimen._100sdp)
           NativeAdSize.LARGE->resources.getDimensionPixelSize(R.dimen._150sdp)
       }
       val params = ad_parent_view.layoutParams
       params.height = height
       ad_parent_view.layoutParams = params
       invalidate()
       attributes.recycle()
   }

    override fun hideAd() {
        val params = ad_parent_view.layoutParams
        params.height = resources.getDimensionPixelSize(R.dimen._0dp)
        ad_parent_view.layoutParams = params
        invalidate()
    }
}

