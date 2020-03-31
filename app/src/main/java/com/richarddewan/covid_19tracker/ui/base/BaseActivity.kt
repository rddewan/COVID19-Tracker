package com.richarddewan.covid_19tracker.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.richarddewan.covid_19tracker.MyApplication
import com.richarddewan.covid_19tracker.R
import com.richarddewan.covid_19tracker.di.component.ActivityComponent
import com.richarddewan.covid_19tracker.di.component.DaggerActivityComponent
import com.richarddewan.covid_19tracker.di.module.ActivityModule
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.alert
import org.jetbrains.anko.support.v4.alert
import javax.inject.Inject

abstract class BaseActivity<VM: BaseViewModel>: AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM
    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        setUpObserver()
        setUpView(savedInstanceState)
        viewModel.onCreate()
    }

    private fun buildActivityComponent() = DaggerActivityComponent.builder()
        .applicationComponent((application as MyApplication).applicationComponent)
        .activityModule(ActivityModule(this))
        .build()

    protected open fun setUpObserver(){
        viewModel.messageString.observe(this, Observer {
            it?.run {
                showErrorDialog(this)
            }
        })

        viewModel.isInternetConnected.observe(this, Observer {
            if (!it){
                showNoInterDialog()
            }
        })
    }

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun setUpView(savedInstanceState: Bundle?)

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    private fun showErrorDialog(msg: String){
        alert {
            isCancelable = false
            title = getString(R.string.error_title)
            message = msg
            positiveButton("OK") {
                it.dismiss()
            }
        }.show()
    }

    private fun showNoInterDialog(){
        alert {
            isCancelable = false
            title = getString(R.string.no_internet_title)
            message = getString(R.string.no_internet_connection)
            positiveButton("OK") {
                it.dismiss()
            }
        }.show()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}