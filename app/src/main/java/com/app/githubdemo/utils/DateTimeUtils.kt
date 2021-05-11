package com.app.githubdemo.utils

import java.text.SimpleDateFormat

object DateTimeUtils {
    fun getDateTime(dateTime: String): String {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val outputDateFormat = SimpleDateFormat("MMM-dd HH:mm")
        return outputDateFormat.format(inputDateFormat.parse(dateTime))
    }
}