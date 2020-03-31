package com.richarddewan.covid_19tracker.data.remote

import com.richarddewan.covid_19tracker.data.remote.response.CountriesResponse
import com.richarddewan.covid_19tracker.data.remote.response.TotalCasesResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface NetworkService {

    @GET(Endpoints.ALL_COUNTRY_SORT)
    fun getAllCountryBySort(@Query("sort") value: String): Single<CountriesResponse>

    @GET(Endpoints.ALL)
    fun getTotalCases(): Single<TotalCasesResponse>
}