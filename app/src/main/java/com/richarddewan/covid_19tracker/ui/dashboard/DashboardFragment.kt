package com.richarddewan.covid_19tracker.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.richarddewan.covid_19tracker.R
import com.richarddewan.covid_19tracker.di.component.FragmentComponent
import com.richarddewan.covid_19tracker.ui.base.BaseFragment
import com.richarddewan.covid_19tracker.util.GeneralHelper
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.support.v4.alert

class DashboardFragment : BaseFragment<DashboardViewModel>() {


    override fun provideLayoutId(): Int = R.layout.fragment_dashboard

    override fun setupView(view: View) {

    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.isLoading.observe(this, Observer {
            pb_dashboard.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.totalCases.observe(this, Observer {
            dataCases.text = it.cases.toString()
            dataDeaths.text = it.deaths.toString()
            dataRecovered.text = it.recovered.toString()
            dataActive.text = it.active.toString()
            dataDateTime.text = GeneralHelper.convertLongToDateTime(it.updated)
        })

    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)

    }

}
