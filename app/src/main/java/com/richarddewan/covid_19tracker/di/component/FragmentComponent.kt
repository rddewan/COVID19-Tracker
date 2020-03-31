package com.richarddewan.covid_19tracker.di.component

import com.richarddewan.covid_19tracker.di.module.FragmentModule
import com.richarddewan.covid_19tracker.di.scope.FragmentScope
import com.richarddewan.covid_19tracker.ui.dashboard.DashboardFragment
import com.richarddewan.covid_19tracker.ui.home.HomeFragment
import dagger.Component


@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(fragment: HomeFragment)

    fun inject(fragment: DashboardFragment)
}