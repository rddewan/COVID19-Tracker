package com.richarddewan.covid_19tracker.ui.dashboard


import androidx.lifecycle.MutableLiveData
import com.richarddewan.covid_19tracker.data.remote.response.TotalCasesResponse
import com.richarddewan.covid_19tracker.data.repository.DashboardRepository
import com.richarddewan.covid_19tracker.ui.base.BaseViewModel
import com.richarddewan.covid_19tracker.util.Logger
import com.richarddewan.covid_19tracker.util.network.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DashboardViewModel(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val dashboardRepository: DashboardRepository
) : BaseViewModel(compositeDisposable, networkHelper) {

    companion object {
        const val TAG = "DashboardViewModel"
    }

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
                            Logger.e(TAG,it.toString())
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