package com.richarddewan.covid_19tracker.data.repository

import com.richarddewan.covid_19tracker.data.remote.NetworkService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class HomeRepository @Inject constructor(private val networkService: NetworkService) {

    fun getAllCountryBySort(value: String) = networkService.getAllCountryBySort(value)
}