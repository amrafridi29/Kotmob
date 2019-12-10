package com.kotmob.admoblib.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.kotmob.admoblib.R
import com.kotmob.admoblib.exceptions.NativeAdIdRequiredException
import com.kotmob.admoblib.models.AdViewType
import com.kotmob.admoblib.models.NativeAdSize
import kotlinx.android.synthetic.main.native_grid_ad.view.*

class KotMobNativeAd (context : Context , attrs : AttributeSet) : NativeAdView(context, attrs){
    init {

        val attributes = context.obtainStyledAttributes(attrs , R.styleable.KotMobNativeAd , 0,0)
        val buttonShapeRes =attributes.getResourceId(R.styleable.KotMobNativeAd_button_shape , R.drawable.rounded_button_bg)
        val buttonTextColor = attributes.getColor(R.styleable.KotMobNativeAd_button_text_color,defaultButtonTextColor)
        val titleTextColor = attributes.getColor(R.styleable.KotMobNativeAd_headline_text_color,defaultTextColor)
        val bodyTextColor = attributes.getColor(R.styleable.KotMobNativeAd_body_text_color,defaultTextColor)
        val isBody = attributes.getBoolean(R.styleable.KotMobNativeAd_is_body, true)
        val adBackgroundColor = attributes.getColor(R.styleable.KotMobNativeAd_ad_background_color ,defaultButtonBackgroundColor)
        val adBackgroundDrawable = attributes.getResourceId(R.styleable.KotMobNativeAd_ad_background_drawable , -1)
        mNativeAdId = attributes.getString(R.styleable.KotMobNativeAd_native_ad_id) ?: throw NativeAdIdRequiredException()

        val viewType = AdViewType.values()[attributes.getInt(R.styleable.KotMobNativeAd_ad_view_type , 0)]
        when(viewType){
            AdViewType.NATIVE_BANNER-> {
                isMedia = false
                inflate(context , R.layout.native_banner_ad ,  this)
            }
            AdViewType.NATIVE_BANNER_MEDIUM-> inflate(context , R.layout.native_banner_opt_ad ,  this)
            AdViewType.NATIVE_BANNER_LARGE-> inflate(context , R.layout.native_banner_opt_ad ,  this)
            AdViewType.NATIVE_GRID-> inflate(context , R.layout.native_grid_ad ,  this)
        }


        ad_call_to_action.setBackgroundResource(buttonShapeRes)
        ad_call_to_action.setTextColor(buttonTextColor)

        ad_headline.setTextColor(titleTextColor)

        ad_body.visibility = when(isBody){
            true -> View.VISIBLE
            false -> View.GONE
        }
        ad_body.setTextColor(bodyTextColor)

        ad_parent_view.setCardBackgroundColor(adBackgroundColor)
        if(adBackgroundDrawable>-1){
            ad_parent_view.setBackgroundResource(adBackgroundDrawable)
        }

        progressBar =progress_bar
        unifiedNativeAdView = uniform

       when(viewType){
            AdViewType.NATIVE_BANNER_MEDIUM->{
                val params = ad_parent_view.layoutParams
                params.height = resources.getDimensionPixelSize(R.dimen._100sdp)
                ad_parent_view.layoutParams = params
            }
            AdViewType.NATIVE_BANNER_LARGE->{
                val params = ad_parent_view.layoutParams
                params.height = resources.getDimensionPixelSize(R.dimen._150sdp)
                ad_parent_view.layoutParams = params
            }
           else->{}
        }

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