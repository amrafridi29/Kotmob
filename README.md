


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
    implementation 'com.github.amrafridi29:Kotmob:0.1.0'
}

allprojects {
    repositories {
       ...
      maven { url 'https://jitpack.io' }
     }
   }
```

Usage
-----
```xml
   <com.kotmob.admoblib.ui.BannerAd
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:banner_ad_id="@string/banner_ad_id"
        />

    <com.kotmob.admoblib.ui.NativeBannerOptAd
        android:id="@+id/mAd"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:ad_size="MEDIUM"
        app:native_ad_id="@string/native_ad_id"/>

    <com.kotmob.admoblib.ui.NativeGridAd
        android:id="@+id/mAd1"
        android:visibility="gone"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        app:native_ad_id="@string/native_ad_id"/>

    <com.kotmob.admoblib.ui.NativeBannerAd
        android:id="@+id/mAd2"
        android:visibility="gone"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:native_ad_id="@string/native_ad_id"/>

```


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
