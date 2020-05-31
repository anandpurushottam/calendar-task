package com.example.calendertask.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.calendertask.data.api.FireBaseHelper
import com.example.calendertask.utils.AppConstant
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener


/**
 * Created by Anand on 30,May,2020
 */
class Repository(private val helper: FireBaseHelper) : ValueEventListener {

    val priceDataML = MutableLiveData<Map<String, String>>()
    val errorML = MutableLiveData<String>()
    val updateDataML = MutableLiveData<String>()
    val loadingML = MutableLiveData<Boolean>().apply { postValue(true)}

    private val ref: DatabaseReference by lazy {
        helper.getDbRef(AppConstant.PATH)
    }

    fun listenToDbChanges() = ref.addValueEventListener(this)


    fun updateDb(data: Map<String, String>) {
        ref.setValue(
            data
        ) { error, ref ->
            if (error != null) {
                errorML.postValue(error.message)
            } else {
                updateDataML.postValue("Data updated successfully.")
            }
        }
    }

    override fun onDataChange(dataSnapshot: DataSnapshot) {
        loadingML.postValue(false)
        val data = dataSnapshot.value
        var map: Map<String, String>? = null

        if (data != null) map = data as Map<String, String>

        priceDataML.postValue(map)
    }

    override fun onCancelled(error: DatabaseError) {
        loadingML.postValue(false)
        errorML.postValue(error.message)
    }


    fun onClear() {
        helper.getDbRef(AppConstant.PATH).removeEventListener(this)
    }
}