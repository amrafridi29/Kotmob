package com.kotmob.admoblib

import android.content.Context
import com.kotmob.admoblib.ads.NativeAd

fun Context.nativeAd(init : NativeAd.Builder.()-> Unit) = NativeAd.Builder().apply { init() }.build()