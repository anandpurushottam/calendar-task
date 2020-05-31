package com.example.calendertask.ui.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.calendertask.R
import com.example.calendertask.data.api.FireBaseHelper
import com.example.calendertask.databinding.ActivityMainBinding
import com.example.calendertask.ui.viewmodel.DailyPriceVM
import com.example.calendertask.ui.viewmodel.factory.ViewModelFactory
import com.example.calendertask.utils.AppConstant
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: DailyPriceVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        observeChanges()

        vm.fetchPrice()
        binding.fab.setOnClickListener { vm.writeData() }

    }


    private fun observeChanges() {
        vm.priceDataML.observe(this, Observer { priceData ->
            Log.d(TAG, "Value is: $priceData")
            binding.calender.setPriceMap(priceData)
        })

        vm.updateDataML.observe(this, Observer { message ->
            Snackbar.make(binding.fab, message, Snackbar.LENGTH_LONG).show();
        })

        vm.errorML.observe(this, Observer {
            Log.w(TAG, "Failed to read value. $it")
        })
        vm.loadingML.observe(this, Observer {
            binding.showProgress=it
        })
    }

    private fun setupViewModel() {
        vm = ViewModelProvider(
            this,
            ViewModelFactory(FireBaseHelper())
        ).get(DailyPriceVM::class.java)
    }

    companion object {
        val TAG = "MainActivity"
    }
}
