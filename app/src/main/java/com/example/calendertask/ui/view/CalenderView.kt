package com.example.calendertask.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CalendarView
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.example.calendertask.R
import com.example.calendertask.databinding.CustomCalenderViewBinding
import com.example.calendertask.ui.viewmodel.CalenderViewVM
import com.example.calendertask.utils.AppConstant.DATE_FORMAT
import com.example.calendertask.utils.AppConstant.DATE_FORMAT_SERVER
import com.example.calendertask.utils.Util.getFormattedDate
import java.util.*


/**
 * Created by Anand on 30,May,2020
 */
class CalenderView : LinearLayout, CalendarView.OnDateChangeListener {


    private lateinit var binding: CustomCalenderViewBinding
    private lateinit var calenderViewVM: CalenderViewVM


    constructor(context: Context) : super(context) {
        initializeViews(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initializeViews(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initializeViews(context)
    }

    private fun initializeViews(context: Context) {
        this.orientation = VERTICAL
        val inflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(inflater, R.layout.custom_calender_view, this, true)

        setUpVm()

        binding.calender.setOnDateChangeListener(this)
    }

    private fun setUpVm() {
        calenderViewVM = CalenderViewVM()
        calenderViewVM.setCalendarLimit(this)
        binding.vm = calenderViewVM
    }

    fun setPriceMap(priceData: Map<String, String>?) {
        if (priceData == null) {
            return
        }
        calenderViewVM.priceData = priceData
        setDefault(calenderViewVM)

    }

    private fun setDefault(calenderViewVM: CalenderViewVM) {
        val date = Date(binding.calender.date)
        val cal = Calendar.getInstance()
        cal.time = date

        calenderViewVM.selectedDate = getFormattedDate(cal.time, DATE_FORMAT)
        calenderViewVM.price =
            calenderViewVM.getDayPricePrice(getFormattedDate(cal.time, DATE_FORMAT_SERVER))
        binding.vm = calenderViewVM
    }


    fun setMaxDate(maxDate: Date) {
        binding.calender.maxDate = maxDate.time
    }

    fun setMinDate(minDate: Date) {
        binding.calender.minDate = minDate.time

    }

    override fun onSelectedDayChange(view: CalendarView, year: Int, month: Int, dayOfMonth: Int) {
        val cal = Calendar.getInstance()
        cal.set(year, month, dayOfMonth)

        calenderViewVM.selectedDate = getFormattedDate(cal.time, DATE_FORMAT)
        calenderViewVM.price =
            calenderViewVM.getDayPricePrice(getFormattedDate(cal.time, DATE_FORMAT_SERVER))
        binding.vm = calenderViewVM
    }

}
