


# Kotmob
A Simple library to show Google Native , Banner and Interstitial Admob ads

## Current Release
The latest version is 
(https://jitpack.io/#amrafridi29/Kotmob/Tag)


Gradle
------
```
dependencies {
    ...
    implementation 'com.github.amrafridi29:Kotmob:0.1.1'
}

allprojects {
    repositories {
       ...
      maven { url 'https://jitpack.io' }
     }
   }
```

Usage XML
-----
```xml
   <com.kotmob.admoblib.ui.BannerAd
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:banner_ad_id="@string/banner_ad_id"
        />

    <com.kotmob.admoblib.ui.NativeBannerOptAd
        android:id="@+id/nativead"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:ad_size="MEDIUM"
        app:native_ad_id="@string/native_ad_id"/>

    <com.kotmob.admoblib.ui.NativeGridAd
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        app:native_ad_id="@string/native_ad_id"/>

    <com.kotmob.admoblib.ui.NativeBannerAd
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:native_ad_id="@string/native_ad_id"/>

```

Kotlin Usage
---
```
call this in your activity or fragment

nativeAd {
   with(this@MainActivity)
   adView(nativead)
   setAdListener {
      when(it){
       is OnAdListener.OnAdFailedToLoad->it.loadAgain()
       is OnAdListener.OnAdLoaded->it.bindAdView()
     }
   }
 }.load()

```
InterstitialAd Usage
---
```
 class App : AdsApplication(){

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initInterstitial(getString(R.string.interstitial_ad_id))
    }
}

Add this in your activity or fragment

App.instance.showInterstitial {
    //do whatever you want
    startActivity(Intent(this, StartActivity::class.java))
}

```
Available Methods Using NativeAd
---
- adChoicOption(adChoiceOption: ChoiceOption)
- isMedia(isMedia: Boolean)
- isBody(isBody: Boolean) 
- isIcon(isIcon: Boolean)
- isLoader(isLoader: Boolean)

Changelog
---------

* **0.1.0**
    * Initial release

License
-------

    Copyright 2019 Muhammad Amir

    Licensed under the Apache License, Version 1.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
