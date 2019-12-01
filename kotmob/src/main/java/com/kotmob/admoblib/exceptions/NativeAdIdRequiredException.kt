package com.kotmob.admoblib.exceptions

class NativeAdIdRequiredException  : Exception(){
    override val message: String
        get() = "Native ad id not provided in xml e.g app:native_ad_id=''"
}