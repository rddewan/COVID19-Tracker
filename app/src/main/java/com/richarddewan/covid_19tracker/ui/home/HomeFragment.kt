package com.richarddewan.covid_19tracker.ui.home

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.richarddewan.covid_19tracker.R
import com.richarddewan.covid_19tracker.data.remote.response.CountriesResponseItem
import com.richarddewan.covid_19tracker.di.component.FragmentComponent
import com.richarddewan.covid_19tracker.ui.base.BaseFragment
import com.richarddewan.covid_19tracker.ui.home.adaptor.CountriesAdaptor
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class HomeFragment : BaseFragment<HomeViewModel>() {

    companion object {
        const val TAG = "HomeFragment"
    }
    private lateinit var recycleView: RecyclerView
    private lateinit var countriesAdaptor: CountriesAdaptor
    private lateinit var searchView: SearchView

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun provideLayoutId(): Int = R.layout.fragment_home

    override fun setupView(view: View) {
        setHasOptionsMenu(true)
        recycleView = rv_home
        recycleView.layoutManager = linearLayoutManager

    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.isLoading.observe(this, Observer {

        })

        viewModel.list?.observe(this, Observer {data->
            countriesAdaptor = CountriesAdaptor(data)
            setRecycleView()
        })
    }

    private fun setRecycleView(){
        recycleView.adapter = countriesAdaptor
    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu,menu)
        searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        searchView.queryHint = "Search..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val filteredList = filterAdaptor(viewModel.list?.value!!, query!!)
                countriesAdaptor.setFilter(filteredList)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = filterAdaptor(viewModel.list?.value!!, newText!!)
                countriesAdaptor.setFilter(filteredList)

                return true
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return false
    }

    fun filterAdaptor(lists: List<CountriesResponseItem>, query: String): ArrayList<CountriesResponseItem> {
        val filteredList = ArrayList<CountriesResponseItem>()

        for (item in lists) {
            val text = item.country.toLowerCase(Locale.getDefault())
            if (text.contains(query)) {
                filteredList.add(item)
            }
        }

        return filteredList
    }

}
