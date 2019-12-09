package com.kotmob.admoblib.ads

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.kotmob.admoblib.ui.NativeAdView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.formats.*
import com.kotmob.admoblib.R
import com.kotmob.admoblib.isOnline
import com.makeramen.roundedimageview.RoundedImageView

data class NativeAd(
    private var context: Context,
    private var view : NativeAdView,
    private var adChoiceOption : ChoiceOption,
    private var adListener : (OnAdListener)-> Unit,
    private var isMedia : Boolean,
    private var isBody : Boolean,
    private var isIcon : Boolean,
    private var isLoader : Boolean
){

    private lateinit var unifiedNativeAd : UnifiedNativeAd
    private lateinit var adView: UnifiedNativeAdView
    class Builder{
        private lateinit var view : NativeAdView
        private var adChoiceOption : ChoiceOption =
            ChoiceOption.TOP_LEFT
        private lateinit var context: Context
        private  var isMedia : Boolean = true
        private var isBody : Boolean = true
        private var isIcon : Boolean = true
        private var isLoader : Boolean = false
        private lateinit var adListener : (OnAdListener)-> Unit
        fun with(context: Context) = apply { this.context = context }
        fun adView(view : NativeAdView) = apply { this.view = view }
        fun adChoicOption(adChoiceOption: ChoiceOption) = apply { this.adChoiceOption = adChoiceOption }
        fun isMedia(isMedia: Boolean) = apply { this.isMedia = isMedia }
        fun isBody(isBody: Boolean) = apply { this.isBody = isBody }
        fun isIcon(isIcon: Boolean) = apply { this.isIcon = isIcon }
        fun isLoader(isLoader: Boolean) = apply { this.isLoader = isLoader }
        fun setAdListener(adListener : (OnAdListener)-> Unit) = apply { this.adListener = adListener }
        fun build() = NativeAd(
            context,
            view,
            adChoiceOption,
            adListener,
            isMedia,
            isBody,
            isIcon,
            isLoader
        )
    }

    fun load(){

        view.context.isOnline {
            if(it){
                if(isLoader){
                    view.visibility = View.VISIBLE
                    view.progressBar?.visibility = View.VISIBLE
                    view.unifiedNativeAdView?.visibility = View.GONE
                }else{
                    view.visibility = View.GONE
                    view.progressBar?.visibility = View.GONE
                }

                val builder = AdLoader.Builder(context, view.nativeAdId)

                builder.forUnifiedNativeAd { unifiedNativeAd ->
                    view.visibility = View.VISIBLE
                    view.progressBar?.visibility = View.GONE
                    view.unifiedNativeAdView?.visibility = View.VISIBLE
                    this.unifiedNativeAd = unifiedNativeAd
                    this.adView = view.unifiedNativeAdView!!
                    adListener(OnAdListener.OnAdLoaded(this))
                }

                val videoOptions = VideoOptions.Builder()
                    .setStartMuted(true)
                    .build()

                val adOptions = NativeAdOptions.Builder()
                    .setVideoOptions(videoOptions)
                    .build()

                builder.withNativeAdOptions(adOptions)

                val adLoader = builder.withAdListener(object : AdListener() {
                    override fun onAdFailedToLoad(errorCode: Int) {
                        adListener(OnAdListener.OnAdFailedToLoad(errorCode, this@NativeAd))
                    }

                }).withNativeAdOptions(
                    NativeAdOptions.Builder()
                        .setRequestCustomMuteThisAd(true)
                        .setAdChoicesPlacement(when(adChoiceOption){
                            ChoiceOption.TOP_LEFT -> NativeAdOptions.ADCHOICES_TOP_LEFT
                            ChoiceOption.TOP_RIGHT -> NativeAdOptions.ADCHOICES_TOP_RIGHT
                            ChoiceOption.BOTTOM_LEFT -> NativeAdOptions.ADCHOICES_BOTTOM_LEFT
                            ChoiceOption.BOTTOM_RIGHT -> NativeAdOptions.ADCHOICES_BOTTOM_RIGHT
                        })
                        .build()
                )
                    .build()

                adLoader.loadAd(AdRequest.Builder().build())
            }else{
                view.hideAd()
            }
        }

    }

    internal fun populateUnifiedNativeAdView() {

        //Media
        if(isMedia) {
            adView.mediaView = adView.findViewById<View>(R.id.ad_media) as MediaView
            adView.mediaView.setMediaContent(unifiedNativeAd.mediaContent)
            adView.mediaView.setImageScaleType(ImageView.ScaleType.FIT_XY)
        }

        adView.headlineView = adView.findViewById(R.id.ad_headline)

        //Body
        if(isBody){
            adView.bodyView = adView.findViewById(R.id.ad_body)
            if (unifiedNativeAd.body == null) {
                adView.bodyView.visibility = View.INVISIBLE
            } else {
                adView.bodyView.visibility = View.VISIBLE
                (adView.bodyView as TextView).text = unifiedNativeAd.body
            }
        }

        //Icon
        if(isIcon){
            adView.iconView = adView.findViewById(R.id.ad_app_icon)
            if (unifiedNativeAd.icon == null) {
                adView.iconView.visibility = View.GONE
            } else {
                (adView.iconView as RoundedImageView).setImageDrawable(
                    unifiedNativeAd.icon.drawable
                )
                adView.iconView.visibility = View.VISIBLE
            }
        }

        adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
        (adView.headlineView as TextView).text = unifiedNativeAd.headline



        if (unifiedNativeAd.callToAction == null) {
            adView.callToActionView.visibility = View.GONE
        } else {
            adView.callToActionView.visibility = View.VISIBLE
            (adView.callToActionView as Button).text = unifiedNativeAd.callToAction
        }



        if (unifiedNativeAd.adChoicesInfo != null) {
            val choicesView = AdChoicesView(adView.context)
            adView.adChoicesView = choicesView
        }

        adView.setNativeAd(unifiedNativeAd)
        val vc = unifiedNativeAd.videoController
        if (vc.hasVideoContent()) {
            vc.videoLifecycleCallbacks = object : VideoController.VideoLifecycleCallbacks() {}
        }
    }
}






