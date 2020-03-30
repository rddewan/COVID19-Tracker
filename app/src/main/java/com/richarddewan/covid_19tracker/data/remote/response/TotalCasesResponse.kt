package com.richarddewan.covid_19tracker.data.remote.response


import com.google.gson.annotations.SerializedName

data class TotalCasesResponse(
    @SerializedName("cases")
    val cases: Int,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("recovered")
    val recovered: Int,
    @SerializedName("updated")
    val updated: Long,
    @SerializedName("active")
    val active: Int
)