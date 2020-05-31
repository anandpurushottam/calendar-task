package com.example.calendertask.utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap


/**
 * Created by Anand on 30,May,2020
 */

object Util {

    fun geRandomPriceData(): HashMap<String, String> {
        val cal = Calendar.getInstance()
        val minDate = cal.time
        cal.add(Calendar.MONTH, AppConstant.MAX_NUMBER_OF_MONTH)
        val maxDate = cal.time
        return getRandomPriceData(minDate, maxDate)
    }

    private fun getRandomPriceData(startDate: Date, endDate: Date): HashMap<String, String> {
        val dataPriceMap = HashMap<String, String>()

        val calendar = GregorianCalendar()
        calendar.time = startDate

        while (calendar.time.before(endDate)) {
            val date = calendar.time
            val formattedDate = getFormattedDate(date, AppConstant.DATE_FORMAT_SERVER)

            dataPriceMap[formattedDate] = rand(100, 200).toString()

            calendar.add(Calendar.DATE, 1)
        }
        return dataPriceMap
    }


    private fun rand(from: Int, to: Int): Int {
        val random = Random()
        return random.nextInt(to - from) + from
    }

    fun getFormattedDate(date: Date, format: String): String {
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateFormat.format(date).toString()
    }
}