package com.richarddewan.covid_19tracker.util.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.richarddewan.covid_19tracker.util.Logger
import java.io.IOException
import java.net.ConnectException
import javax.inject.Singleton

@Singleton
class NetworkHelper(private val context: Context) {
    companion object {
        private const val TAG = "NetworkHelper"
    }

    fun isNetworkConnected(): Boolean{
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

    fun castToNetworkError(throwable: Throwable): NetworkError {
        val defaultNetworkError = NetworkError()
        try {
            if (throwable is ConnectException) return NetworkError(0, "0")

            if (throwable !is HttpException) return defaultNetworkError

            val error = GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
                .fromJson(throwable.response().errorBody()?.string(), NetworkError::class.java)

            return NetworkError(throwable.code(), error.statusCode, error.message)

        } catch (e: IOException) {
            Logger.e(TAG, e.toString())
        } catch (e: JsonSyntaxException) {
            Logger.e(TAG, e.toString())
        } catch (e: NullPointerException) {
            Logger.e(TAG, e.toString())
        }

        return defaultNetworkError

    }
}