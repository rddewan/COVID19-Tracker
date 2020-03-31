package com.richarddewan.covid_19tracker

import android.app.Application
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.Constants.APP_SECRET
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.richarddewan.covid_19tracker.di.component.ApplicationComponent
import com.richarddewan.covid_19tracker.di.module.ApplicationModule
import com.richarddewan.covid_19tracker.di.component.DaggerApplicationComponent

class MyApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()

        //app center
        AppCenter.start(this,
            BuildConfig.MYAPP_SECRET,
            Analytics::class.java,
            Crashes::class.java)
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