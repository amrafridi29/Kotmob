package com.kotmob.admoblib

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.kotmob.admoblib.ads.NativeAd
import com.kotmob.admoblib.network.NoConnectionInterceptor

fun Context.kotmobNativeAd(init : NativeAd.Builder.()-> Unit) = NativeAd.Builder().apply { init() }.build()
fun Context.isOnline(Callback : (isOnline:Boolean)-> Unit){
    NoConnectionInterceptor(this)
        .intercept(Callback)
}
