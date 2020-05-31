package com.example.calendertask.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.calendertask.data.api.FireBaseHelper
import com.example.calendertask.data.repository.Repository
import com.example.calendertask.ui.viewmodel.DailyPriceVM

class ViewModelFactory(private val fireBaseHelper: FireBaseHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DailyPriceVM::class.java)) {
            return DailyPriceVM(Repository(fireBaseHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}