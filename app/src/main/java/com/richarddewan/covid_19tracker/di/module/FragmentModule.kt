package com.richarddewan.covid_19tracker.di.module

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.richarddewan.covid_19tracker.data.repository.DashboardRepository
import com.richarddewan.covid_19tracker.data.repository.HomeRepository
import com.richarddewan.covid_19tracker.ui.base.BaseFragment
import com.richarddewan.covid_19tracker.ui.dashboard.DashboardViewModel
import com.richarddewan.covid_19tracker.ui.home.HomeViewModel
import com.richarddewan.covid_19tracker.util.ViewModelProviderFactory
import com.richarddewan.covid_19tracker.util.network.NetworkHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FragmentModule(private val fragment: BaseFragment<*>){

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun provideHomeViewModel(
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        homeRepository: HomeRepository
    ): HomeViewModel = ViewModelProvider(fragment,ViewModelProviderFactory(HomeViewModel::class){
        HomeViewModel(
            compositeDisposable, networkHelper, homeRepository
        )
    }).get(HomeViewModel::class.java)

    @Provides
    fun provideDashboardViewModel(compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    dashboardRepository: DashboardRepository): DashboardViewModel =
        ViewModelProvider(fragment,ViewModelProviderFactory(DashboardViewModel::class){
            DashboardViewModel(
                compositeDisposable, networkHelper, dashboardRepository
            )
        }).get(DashboardViewModel::class.java)
}