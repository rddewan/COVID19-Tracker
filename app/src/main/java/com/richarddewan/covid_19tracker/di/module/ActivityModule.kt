package com.richarddewan.covid_19tracker.di.module

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import com.richarddewan.covid_19tracker.ui.base.BaseActivity
import com.richarddewan.covid_19tracker.ui.splash.SplashViewModel
import com.richarddewan.covid_19tracker.util.ViewModelProviderFactory
import com.richarddewan.covid_19tracker.util.network.NetworkHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideSplashViewModel(
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): SplashViewModel =
        ViewModelProvider(activity, ViewModelProviderFactory(SplashViewModel::class) {
            SplashViewModel(compositeDisposable, networkHelper)
        }).get(SplashViewModel::class.java)

}

