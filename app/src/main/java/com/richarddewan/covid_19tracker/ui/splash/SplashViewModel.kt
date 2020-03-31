package com.richarddewan.covid_19tracker.ui.splash

import androidx.lifecycle.MutableLiveData
import com.richarddewan.covid_19tracker.ui.base.BaseViewModel
import com.richarddewan.covid_19tracker.util.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable

class SplashViewModel(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(compositeDisposable, networkHelper) {



    override fun onCreate() {
        isInternetConnected()

    }
}