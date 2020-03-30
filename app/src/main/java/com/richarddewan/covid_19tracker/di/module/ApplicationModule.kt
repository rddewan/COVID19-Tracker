package com.richarddewan.covid_19tracker.di.module

import com.richarddewan.covid_19tracker.BuildConfig
import com.richarddewan.covid_19tracker.MyApplication
import com.richarddewan.covid_19tracker.data.remote.NetworkService
import com.richarddewan.covid_19tracker.data.remote.Networking
import com.richarddewan.covid_19tracker.util.network.NetworkHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
class ApplicationModule(private val application: MyApplication) {

    @Provides
    fun provideApplication() = application

    @Provides
    fun provideContext() = application

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper =
        NetworkHelper(application)

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024
        )

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}