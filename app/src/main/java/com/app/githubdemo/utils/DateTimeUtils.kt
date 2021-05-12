package com.app.githubdemo.utils

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {
    fun getDateTime(dateTime: String): String {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        inputDateFormat.timeZone = TimeZone.getTimeZone("GMT")
        return DateFormat.format("yyyy-MMM-dd hh:mm:ss a", inputDateFormat.parse(dateTime).time)
            .toString()
    }
}