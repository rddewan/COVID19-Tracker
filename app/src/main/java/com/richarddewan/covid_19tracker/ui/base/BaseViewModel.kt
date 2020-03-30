package com.richarddewan.covid_19tracker.ui.base

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.richarddewan.covid_19tracker.R
import com.richarddewan.covid_19tracker.util.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import java.net.HttpURLConnection

abstract class BaseViewModel(
    protected val compositeDisposable: CompositeDisposable,
    protected val networkHelper: NetworkHelper
): ViewModel() {

    val messageString: MutableLiveData<String> = MutableLiveData()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    protected fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    abstract fun onCreate()

    protected fun handleNetworkError(err: Throwable?) =
        err?.let {
            networkHelper.castToNetworkError(it).run {
                when (status){
                    -1 -> messageString.postValue(it.cause.toString())
                    HttpURLConnection.HTTP_UNAUTHORIZED ->
                        messageString.postValue(R.string.permission_denied.toString())
                    HttpURLConnection.HTTP_INTERNAL_ERROR ->
                        messageString.postValue(R.string.network_internal_error.toString())
                    HttpURLConnection.HTTP_UNAVAILABLE ->
                        messageString.postValue(R.string.network_server_not_available.toString())
                    else -> messageString.postValue(message)


                }
            }
        }
}