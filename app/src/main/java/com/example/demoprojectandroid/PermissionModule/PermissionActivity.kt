package com.example.demoprojectandroid.PermissionModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demoprojectandroid.databinding.ActivityPermission2Binding


class PermissionActivity : AppCompatActivity() {

    lateinit var binding: ActivityPermission2Binding

     val REQUEST_CODE_PERMISSIONS = 123
    private val permissionHelper =
        PermissionHelper(this, REQUEST_CODE_PERMISSIONS, arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE))

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityPermission2Binding.inflate(layoutInflater)
            setContentView(binding.root)

    }
}