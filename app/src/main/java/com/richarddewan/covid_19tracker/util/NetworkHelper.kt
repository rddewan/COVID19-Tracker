package com.richarddewan.covid_19tracker.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build

object NetworkHelper {
    fun isNetworkConnected(context: Context): Boolean{
        var result = false
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                result = isCapableNetwork(this,this.activeNetwork)
            }
            else {
                val networkInfo = this.allNetworks
                for (network in networkInfo){
                    if (isCapableNetwork(this,network)) result = true
                }
            }
        }

        return result
    }

    private fun isCapableNetwork(connectivityManager: ConnectivityManager,network: Network?): Boolean{
        connectivityManager.getNetworkCapabilities(network)?.also {
            if (it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                return true
            }
            else if (it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                return true
            }
        }
        return false

    }
}