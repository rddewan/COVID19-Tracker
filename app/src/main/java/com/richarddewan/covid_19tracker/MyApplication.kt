package com.richarddewan.covid_19tracker

import android.app.Application
import com.richarddewan.covid_19tracker.di.component.ApplicationComponent
import com.richarddewan.covid_19tracker.di.module.ApplicationModule
import com.richarddewan.covid_19tracker.di.component.DaggerApplicationComponent

class MyApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies(){
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(
                ApplicationModule(
                    this
                )
            )
            .build()

        applicationComponent.inject(this)


    }
}