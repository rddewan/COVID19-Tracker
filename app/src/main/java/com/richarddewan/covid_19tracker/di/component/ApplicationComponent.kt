package com.richarddewan.covid_19tracker.di.component

import com.richarddewan.covid_19tracker.MyApplication
import com.richarddewan.covid_19tracker.data.remote.NetworkService
import com.richarddewan.covid_19tracker.data.repository.DashboardRepository
import com.richarddewan.covid_19tracker.data.repository.HomeRepository
import com.richarddewan.covid_19tracker.di.module.ApplicationModule
import com.richarddewan.covid_19tracker.util.network.NetworkHelper
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(myApplication: MyApplication)

    fun getNetworkHelper(): NetworkHelper

    fun getNetwokService(): NetworkService

    fun getCompositeDisposable(): CompositeDisposable

    fun getHomeRepository(): HomeRepository

    fun getDashboardRepository(): DashboardRepository
}