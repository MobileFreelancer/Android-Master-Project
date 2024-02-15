package com.example.demoprojectandroid.DialogsLoaders

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.demoprojectandroid.databinding.ActivityCustomDialogsandloaderBinding
import com.example.demoprojectandroid.databinding.DialogAlertBinding

import com.google.android.material.button.MaterialButton

class CustomDialogsandloaderActivity : AppCompatActivity() {

    lateinit var binding: ActivityCustomDialogsandloaderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomDialogsandloaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // place it on base activity


        // Show the custom dialog when needed




        binding.btnShow.setOnClickListener(View.OnClickListener {
            CustomProgressDialog.getCustomProgressDialog(this).showCustomDialog()
        })
        binding.btnHide.setOnClickListener(View.OnClickListener {
            CustomProgressDialog.getCustomProgressDialog(this).dissmissDialog()
        })

        binding.btnShowDialog.setOnClickListener(View.OnClickListener {
            showLoginDialog(this)
        })
    }


    fun showLoginDialog(context: Context?) {
        val dialog = Dialog(context!!)
        val binding = DialogAlertBinding.inflate(dialog.layoutInflater)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)


        binding.btnCancel.setOnClickListener { dialog.dismiss() }
        binding.btnLogin.setOnClickListener {
            // Perform login actions
            dialog.dismiss()
        }

        dialog.show()
    }
}