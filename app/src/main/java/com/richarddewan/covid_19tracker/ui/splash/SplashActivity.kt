package com.richarddewan.covid_19tracker.ui.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import com.richarddewan.covid_19tracker.R
import com.richarddewan.covid_19tracker.di.component.ActivityComponent
import com.richarddewan.covid_19tracker.ui.base.BaseActivity
import com.richarddewan.covid_19tracker.ui.main.MainActivity
import com.richarddewan.covid_19tracker.util.GeneralHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.alert
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.alert

class SplashActivity: BaseActivity<SplashViewModel>() {

    override fun provideLayoutId(): Int = R.layout.activity_splash

    override fun setUpView(savedInstanceState: Bundle?) {
        GeneralHelper.hideStatusBar(this)

        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            if (viewModel.isInternetConnected.value!!){
                finish()
                startActivity(intentFor<MainActivity>())
            }
        }

    }

    override fun setUpObserver() {
        super.setUpObserver()

    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)

    }

    private fun showNoInterDialog(){
        alert {
            isCancelable = false
            title = getString(R.string.error_title)
            message = getString(R.string.no_internet_connection)
            positiveButton("OK") {
                it.dismiss()
            }
        }.show()
    }
}