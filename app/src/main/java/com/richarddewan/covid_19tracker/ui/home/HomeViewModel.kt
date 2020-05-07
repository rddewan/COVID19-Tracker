package com.richarddewan.covid_19tracker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.richarddewan.covid_19tracker.data.remote.response.CountriesResponse
import com.richarddewan.covid_19tracker.data.remote.response.CountriesResponseItem
import com.richarddewan.covid_19tracker.data.repository.HomeRepository
import com.richarddewan.covid_19tracker.ui.base.BaseViewModel
import com.richarddewan.covid_19tracker.util.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val homeRepository: HomeRepository
) : BaseViewModel(compositeDisposable, networkHelper) {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val sortValue: MutableLiveData<String> = MutableLiveData()
    val list: MutableLiveData<ArrayList<CountriesResponseItem>>? = MutableLiveData()
    val isNetworkConnected: MutableLiveData<Boolean> = MutableLiveData()

    override fun onCreate() {
        sortValue.value = "country"
        getAllCountryBySort()

    }

    fun getAllCountryBySort(){

        isInternetConnected()

        if (checkInternetConnection()){

            isLoading.value = true

            compositeDisposable.add(
                homeRepository.getAllCountryBySort(sortValue.value!!)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        { it ->
                            list?.value?.clear()
                            if (sortValue.value == "country") it.sortWith(compareBy { it.country })

                            list?.postValue(it)
                            isLoading.postValue(false)
                        },
                        {
                            isLoading.postValue(false)
                            handleNetworkError(it)
                        }
                    )
            )
        }

    }
}