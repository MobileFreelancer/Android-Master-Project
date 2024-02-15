package com.example.demoprojectandroid.Helper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.demoprojectandroid.R
import com.example.demoprojectandroid.databinding.ActivityApidemoBinding
import com.example.demoprojectandroid.databinding.ActivityHelperBinding

class HelperActivity : AppCompatActivity() {

    lateinit var binding: ActivityHelperBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelperBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.GetLocation.setOnClickListener(View.OnClickListener {

            LocationHelper(this).requestLocationUpdates()
        })


    }

    fun closekeybaord(view: View) {

        Helper.hideKeyboard(this,binding.etEmail)
    }


}