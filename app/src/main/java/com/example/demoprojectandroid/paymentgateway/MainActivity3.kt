package com.example.demoprojectandroid.paymentgateway

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.demoprojectandroid.R
import com.example.demoprojectandroid.databinding.ActivityMain2Binding
import com.example.demoprojectandroid.utils.Constant
import com.stripe.android.PaymentConfiguration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        PaymentConfiguration.init(this, Constant.PUBLISHABLE_KEY)
    }

    override fun onResume() {
        super.onResume()
        initializeNavigation()
    }

    private fun initializeNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        if (navHostFragment != null) {
            navController = navHostFragment.navController
        } else {
            Log.e("MainActivity3", "NavHostFragment is null")
        }
    }
}