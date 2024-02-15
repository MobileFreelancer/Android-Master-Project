package com.example.demoprojectandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.demoprojectandroid.APICall.APIDemoActivity
import com.example.demoprojectandroid.CommonUi.CommonUIActivity
import com.example.demoprojectandroid.Complexui.ComplexActivity
import com.example.demoprojectandroid.DialogsLoaders.CustomDialogsandloaderActivity
import com.example.demoprojectandroid.Helper.HelperActivity
import com.example.demoprojectandroid.ImagePickerCamera.ImageCameraActivity
import com.example.demoprojectandroid.Localization.ChangeLanguageActivity
import com.example.demoprojectandroid.LoginModule.LoginActivity
import com.example.demoprojectandroid.NotificationDemo.NotificationActivity
import com.example.demoprojectandroid.Room.RoomActivity
import com.example.demoprojectandroid.SocialLogin.SocialLoginActivity
import com.example.demoprojectandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Clicklisteners();
    }

    private fun Clicklisteners() {
        binding.loginmodule.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, LoginActivity::class.java));
        })

        binding.tvSociallogin.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, SocialLoginActivity::class.java));
        })

        binding.btnNotification.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java));
        })

        binding.tvImage.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, ImageCameraActivity::class.java));
        })
        binding.tvApicall.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, APIDemoActivity::class.java));
        })
        binding.tvRoomModule.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, RoomActivity::class.java));
        })
        binding.tvCommonui.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, CommonUIActivity::class.java));
        })

        binding.tvComplexui.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, ComplexActivity::class.java));
        })
        binding.tvhelper.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, HelperActivity::class.java));
        })
        binding.tvDialoge.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, CustomDialogsandloaderActivity::class.java));
        })
    }
}