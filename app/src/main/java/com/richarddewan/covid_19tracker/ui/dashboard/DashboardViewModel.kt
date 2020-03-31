package com.richarddewan.covid_19tracker.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.richarddewan.covid_19tracker.data.remote.response.TotalCasesResponse
import com.richarddewan.covid_19tracker.data.repository.DashboardRepository
import com.richarddewan.covid_19tracker.ui.base.BaseViewModel
import com.richarddewan.covid_19tracker.util.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DashboardViewModel(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val dashboardRepository: DashboardRepository
) : BaseViewModel(compositeDisposable, networkHelper) {

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val totalCases: MutableLiveData<TotalCasesResponse> = MutableLiveData()

    override fun onCreate() {
        getTotalCases()

    }

    private fun getTotalCases(){

        isInternetConnected()

        if (checkInternetConnection()){
            isLoading.value = true

            compositeDisposable.add(
                dashboardRepository.getTotalCases()
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            isLoading.postValue(false)
                            totalCases.postValue(it)
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