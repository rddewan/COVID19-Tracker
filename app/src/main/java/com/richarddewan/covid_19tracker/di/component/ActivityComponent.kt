package com.richarddewan.covid_19tracker.di.component

import com.richarddewan.covid_19tracker.di.module.ActivityModule
import com.richarddewan.covid_19tracker.di.scope.ActivityScope
import com.richarddewan.covid_19tracker.ui.main.MainActivity
import com.richarddewan.covid_19tracker.ui.splash.SplashActivity
import dagger.Component


@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: SplashActivity)
}