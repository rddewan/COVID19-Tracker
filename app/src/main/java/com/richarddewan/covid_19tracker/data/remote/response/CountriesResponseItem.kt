package com.richarddewan.covid_19tracker.data.remote.response


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class CountriesResponseItem(
    @Expose
    @SerializedName("country")
    val country: String,
    @Expose
    @SerializedName("countryInfo")
    val countryInfo: CountryInfo,
    @Expose
    @SerializedName("cases")
    val cases: Int,
    @Expose
    @SerializedName("todayCases")
    val todayCases: Int,
    @Expose
    @SerializedName("deaths")
    val deaths: Int,
    @Expose
    @SerializedName("todayDeaths")
    val todayDeaths: Int,
    @Expose
    @SerializedName("recovered")
    val recovered: Int,
    @Expose
    @SerializedName("active")
    val active: Int,
    @Expose
    @SerializedName("critical")
    val critical: Int,
    @Expose
    @SerializedName("casesPerOneMillion")
    val casesPerOneMillion: Double,
    @Expose
    @SerializedName("deathsPerOneMillion")
    val deathsPerOneMillion: Double,
    @Expose
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