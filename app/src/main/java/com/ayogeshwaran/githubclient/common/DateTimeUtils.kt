package com.ayogeshwaran.githubclient.common

import android.icu.text.DateFormat
import android.os.Build
import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {
    const val ISO_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    private const val MONTH_DATE_FORMAT = "MMM d"
    private const val ONLY_YEAR = "yyyy"

    fun getFormattedDate(date: Date): String {
        val locale = Locale.getDefault()
        // Check if date is date is today
        if (DateUtils.isToday(date.time)) {
            return SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT, locale).format(date)
        } else if (isCurrentYear(date)) {
            // If date is not today but year is same as today
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                DateFormat.getPatternInstance(DateFormat.ABBR_MONTH_DAY, locale).format(date)
            } else {
                SimpleDateFormat(MONTH_DATE_FORMAT, locale).format(date)
            }
        }
        // If date is not today and year is not the same as today
        return SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, locale).format(date)
    }

    private fun isCurrentYear(date: Date): Boolean {
        return Calendar.getInstance().get(Calendar.YEAR).toString() == SimpleDateFormat(
            ONLY_YEAR,
            Locale.getDefault()
        ).format(date)
    }

}
