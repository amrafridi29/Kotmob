package com.kotmob.admoblib.exceptions

class AppUrlRequiredException : Exception(){
    override val message: String?
        get() = "App url not provided in xml e.g app:ad_app_url=''"
}