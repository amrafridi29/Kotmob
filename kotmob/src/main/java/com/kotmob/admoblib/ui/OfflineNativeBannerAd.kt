package com.kotmob.admoblib.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.AttributeSet
import android.view.View
import com.kotmob.admoblib.R
import android.widget.*
import com.kotmob.admoblib.exceptions.AppUrlRequiredException
import kotlinx.android.synthetic.main.offline_native_banner_ad.view.*


class OfflineNativeBannerAd (context: Context, attrs: AttributeSet): LinearLayout(context, attrs){
   init {
       inflate(context ,R.layout.offline_native_banner_ad ,  this)
       val attributes = context.obtainStyledAttributes(attrs , R.styleable.OfflineNativeBannerAd , 0,0)
       val buttonShapeRes =attributes.getResourceId(R.styleable.OfflineNativeBannerAd_button_shape , R.drawable.rounded_button_bg)
       val buttonTextColor = attributes.getColor(R.styleable.OfflineNativeBannerAd_button_text_color,Color.WHITE)
       val titleTextColor = attributes.getColor(R.styleable.OfflineNativeBannerAd_headline_text_color,Color.WHITE)
       val bodyTextColor = attributes.getColor(R.styleable.OfflineNativeBannerAd_body_text_color,Color.WHITE)
       val isBody = attributes.getBoolean(R.styleable.OfflineNativeBannerAd_is_body, true)

       val title = attributes.getString(R.styleable.OfflineNativeBannerAd_ad_title)
       val body = attributes.getString(R.styleable.OfflineNativeBannerAd_ad_body)
       val buttonText = attributes.getString(R.styleable.OfflineNativeBannerAd_ad_button_text)

       val iconRes = attributes.getResourceId(R.styleable.OfflineNativeBannerAd_ad_icon , 0)

       val url = attributes.getString(R.styleable.OfflineNativeAd_ad_app_url) ?: throw AppUrlRequiredException()



       ad_app_icon.setImageResource(iconRes)

       ad_call_to_action.setBackgroundResource(buttonShapeRes)
       ad_call_to_action.setTextColor(buttonTextColor)
       ad_call_to_action.text = buttonText

       ad_headline.setTextColor(titleTextColor)
       ad_headline.text= title

        ad_body.visibility = when(isBody){
            true -> View.VISIBLE
            false -> View.GONE
        }
       ad_body.setTextColor(bodyTextColor)
       ad_body.text= body

       ad_call_to_action.setOnClickListener {

               val browserIntent =
                   Intent(Intent.ACTION_VIEW, Uri.parse(url))
               context.startActivity(browserIntent)

       }

       invalidate()

       attributes.recycle()
   }
}