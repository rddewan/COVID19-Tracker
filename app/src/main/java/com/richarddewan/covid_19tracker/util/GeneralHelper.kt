package com.richarddewan.covid_19tracker.util

import android.app.Activity
import android.view.WindowManager
import com.richarddewan.covid_19tracker.data.remote.response.CountriesResponseItem.Companion.setDateTime
import java.text.SimpleDateFormat
import java.util.*

object GeneralHelper {

    fun convertLongToDateTime(time: Long?): String{
        val date = time?.let { Date(it) }
        val format = SimpleDateFormat("dd.MM.yyyy hh:mm a", Locale.getDefault())
        return format.format(date!!)
    }

    fun hideStatusBar(activity: Activity){
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}