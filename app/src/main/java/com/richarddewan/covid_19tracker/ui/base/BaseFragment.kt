package com.richarddewan.covid_19tracker.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.richarddewan.covid_19tracker.MyApplication
import com.richarddewan.covid_19tracker.R
import com.richarddewan.covid_19tracker.di.component.DaggerFragmentComponent
import com.richarddewan.covid_19tracker.di.component.FragmentComponent
import com.richarddewan.covid_19tracker.di.module.FragmentModule
import org.jetbrains.anko.support.v4.alert
import javax.inject.Inject

abstract class BaseFragment<VM: BaseViewModel>: Fragment() {

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildFragmentComponent())
        super.onCreate(savedInstanceState)
        setupObservers()
        viewModel.onCreate()
    }

    private fun buildFragmentComponent() = DaggerFragmentComponent
        .builder()
        .applicationComponent((context!!.applicationContext as MyApplication).applicationComponent)
        .fragmentModule(FragmentModule(this))
        .build()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(provideLayoutId(),container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)

        viewModel.isInternetConnected.observe(viewLifecycleOwner, Observer {
            if (!it){
                showNoInterDialog()
            }
        })
    }

    protected open fun setupObservers(){
        viewModel.messageString.observe(this, Observer {
            it?.run {
                showErrorDialog(this)
            }
        })

    }

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun setupView(view: View)

    protected abstract fun injectDependencies(fragmentComponent: FragmentComponent)

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
}