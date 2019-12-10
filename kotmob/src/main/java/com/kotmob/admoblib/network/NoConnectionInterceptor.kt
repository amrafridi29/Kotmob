package com.kotmob.admoblib.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

internal class NoConnectionInterceptor(private val context: Context){
     fun intercept(isOnline : (Boolean)-> Unit){
         if(isConnectionOn()){
             isInternetAvailable {
                 isOnline(it)
             }
         }else{
             isOnline(false)
         }

    }

    private fun isConnectionOn(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val connection = connectivityManager.getNetworkCapabilities(network)
            return connection != null && (
                    connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
        } else {
            val activeNetwork = connectivityManager.activeNetworkInfo
            if (activeNetwork != null) {
                return (activeNetwork.type == ConnectivityManager.TYPE_WIFI ||
                        activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
            }
            return false
        }
    }

    private fun isInternetAvailable(isAvailable : (Boolean)-> Unit) {
       doAsync {
            try {
               val timeoutMs = 1500
               val sock = Socket()
               val sockaddr = InetSocketAddress("8.8.8.8", 53)

               sock.connect(sockaddr, timeoutMs)
               sock.close()

               uiThread{isAvailable(true)}
           } catch (e: IOException) {
                uiThread{isAvailable(false)}
           }
       }



    }

    class NoConnectivityException : IOException() {
        override val message: String
            get() = "No network available, please check your WiFi or Data connection"
    }

    class NoInternetException() : IOException() {
        override val message: String
            get() = "No internet available, please check your connected WIFi or Data"
    }
}