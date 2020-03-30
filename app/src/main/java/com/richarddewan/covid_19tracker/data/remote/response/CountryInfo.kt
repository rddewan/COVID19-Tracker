package com.richarddewan.covid_19tracker.data.remote.response


import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName

data class CountryInfo(
    @SerializedName("_id")
    val id: Int,
    @SerializedName("country")
    val country: String,
    @SerializedName("iso2")
    val iso2: String,
    @SerializedName("iso3")
    val iso3: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("long")
    val long: Double,
    @SerializedName("flag")
    val flag: String

){
    companion object {

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun ImageView.setImageUrl(url: String?) {
            if (url != null) {
                Glide.with(context)
                    .load(url)
                    .circleCrop()
                    .into(this)
            } else {
                this.visibility = View.GONE
            }

        }
    }
}