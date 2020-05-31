package com.example.calendertask.data.api

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * Created by Anand on 30,May,2020
 */

    class FireBaseHelper {

    private fun getFireBaseDb(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    fun getDbRef(path: String): DatabaseReference {
        return getFireBaseDb().getReference(path)
    }

}
