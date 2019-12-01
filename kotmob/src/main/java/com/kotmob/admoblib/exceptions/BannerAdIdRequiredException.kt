package com.kotmob.admoblib.exceptions

class BannerAdIdRequiredException : Exception(){
    override val message: String
        get() = "Banner ad id not provided in xml e.g app:banner_ad_id=''"
}