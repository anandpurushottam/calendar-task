package com.example.calendertask.ui.viewmodel

import com.example.calendertask.ui.view.CalenderView
import com.example.calendertask.utils.AppConstant
import java.util.*

/**
 * Created by Anand on 30,May,2020
 */

class CalenderViewVM {

    var selectedDate: String = ""
    var price: String = ""
    var priceData: Map<String, String>? = null

    fun setCalendarLimit(calender: CalenderView) {
        val cal = Calendar.getInstance()
        val minDate = cal.time

        cal.time = Date()
        cal.add(Calendar.DATE, AppConstant.MAX_NUMBER_OF_MONTH * 30)

        val maxDate = cal.time

        calender.setMinDate(minDate)
        calender.setMaxDate(maxDate)
    }

    fun getDayPricePrice(date: String): String {
        val price = priceData?.get(date)

        return if (price.isNullOrEmpty()) {
            "N/A"
        } else
            "₹ $price"
    }

}