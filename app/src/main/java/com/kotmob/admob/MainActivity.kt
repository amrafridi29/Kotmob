package com.kotmob.admob

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.kotmob.admoblib.ads.OnAdListener
import com.kotmob.admoblib.kotmobNativeAd

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            App.instance.showInterstitial {
                Toast.makeText(this , "Loaded" , Toast.LENGTH_SHORT).show()
            }
        }

        kotmobNativeAd  {
            with(this@MainActivity)
            adView(mAd)
            isLoader(true)
            setAdListener {
                when (it) {
                    is OnAdListener.OnAdLoaded -> it.bindAdView()
                }
            }
        }.load()

       /* nativeAd {
            with(this@MainActivity)
            adView(mAd1)
            setAdListener {
                when (it) {
                    is OnAdListener.OnAdLoaded -> it.bindAdView()
                }
            }
        }.load()

        nativeAd {
            with(this@MainActivity)
            adView(mAd2)
            isMedia(false)
            adChoicOption(ChoiceOption.BOTTOM_LEFT)
            setAdListener {
                when (it) {
                    is OnAdListener.OnAdLoaded -> it.bindAdView()
                }
            }
        }.load()*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
