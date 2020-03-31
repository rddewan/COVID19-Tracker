package com.richarddewan.covid_19tracker.ui.home.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.richarddewan.covid_19tracker.R
import com.richarddewan.covid_19tracker.data.remote.response.CountriesResponseItem
import com.richarddewan.covid_19tracker.databinding.CountriesListViewBinding

class CountriesAdaptor(private var list: ArrayList<CountriesResponseItem>): RecyclerView.Adapter<CountriesAdaptor.ViewHolder>() {

    fun setFilter(list:ArrayList<CountriesResponseItem>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: CountriesListViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
        R.layout.countries_list_view,parent,false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list.size > 0 ){
            val data: CountriesResponseItem = list[position]

            holder.countriesList.data = data
        }

    }

    class ViewHolder(binding: CountriesListViewBinding): RecyclerView.ViewHolder(binding.root){
        var countriesList: CountriesListViewBinding = binding
    }
}