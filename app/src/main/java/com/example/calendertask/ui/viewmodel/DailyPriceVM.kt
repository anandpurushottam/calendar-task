package com.example.calendertask.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.calendertask.data.repository.Repository
import com.example.calendertask.ui.view.CalenderView
import com.example.calendertask.utils.AppConstant.MAX_NUMBER_OF_MONTH
import com.example.calendertask.utils.Util
import java.util.*

/**
 * Created by Anand on 30,May,2020
 */
class DailyPriceVM(private val repository: Repository) : ViewModel() {

    val priceDataML = repository.priceDataML
    val errorML = repository.errorML
    val updateDataML=repository.updateDataML
    val loadingML=repository.loadingML

    fun fetchPrice() {
        repository.listenToDbChanges()
    }

    fun writeData() {
        repository.updateDb(Util.geRandomPriceData())
    }




    override fun onCleared() {
        super.onCleared()
        repository.onClear()
    }

}
