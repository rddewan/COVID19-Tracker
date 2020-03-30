package com.richarddewan.covid_19tracker.data.remote.response


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class CountriesResponseItem(
    @SerializedName("country")
    val country: String,
    @SerializedName("countryInfo")
    val countryInfo: CountryInfo,
    @SerializedName("cases")
    val cases: Int,
    @SerializedName("todayCases")
    val todayCases: Int,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("todayDeaths")
    val todayDeaths: Int,
    @SerializedName("recovered")
    val recovered: Int,
    @SerializedName("active")
    val active: Int,
    @SerializedName("critical")
    val critical: Int,
    @SerializedName("casesPerOneMillion")
    val casesPerOneMillion: Double,
    @SerializedName("deathsPerOneMillion")
    val deathsPerOneMillion: Double,
    @SerializedName("updated")
    val updated: Long
){
    companion object {

        @JvmStatic
        @BindingAdapter("dateTime")
        fun TextView.setDateTime(time: Long?) {
            val date = time?.let { Date(it) }
            val format = SimpleDateFormat("dd.MM.yyyy hh:mm a",Locale.getDefault())
            this.text = format.format(date!!)

        }
    }
}