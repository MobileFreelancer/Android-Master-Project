package com.example.demoprojectandroid.LoginModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.demoprojectandroid.R
import com.example.demoprojectandroid.databinding.ActivityLogin2Binding
import com.example.demoprojectandroid.databinding.ActivityLoginBinding
import com.example.demoprojectandroid.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding:  ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.btnLogin.setOnClickListener {

            if (isValidCredentials()) {
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()

            }
        }

    }
    private fun isValidCredentials(): Boolean {

        if (!isValidEmail(binding.etEmail.text.toString())) {
            Toast.makeText(this, "Email is not valid", Toast.LENGTH_SHORT).show()
            return false
        } else if (!isValidUsername(binding.etUsername.text.toString())) {
            Toast.makeText(this, "Username should be at least 5 characters long", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.etPassword.text.isEmpty() || !isValidPassword(binding.etPassword.text.toString())) {
            Toast.makeText(this, "Password should be at least 5 characters long and contain both lowercase & uppercase letters", Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }

    }


    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return email.isNotEmpty() && email.matches(emailRegex)
    }

    fun isValidUsername(username: String): Boolean {
        // Username should be at least 5 characters long and can contain only lowercase and uppercase letters
        val usernameRegex = Regex("^[a-zA-Z]{5,}$")
        return username.isNotEmpty() && username.matches(usernameRegex)
    }

    fun isValidPassword(password: String): Boolean {
        // Password should be at least 5 characters long and contain both lowercase and uppercase letters
        val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z]).{5,}\$")
        return password.isNotEmpty() && password.matches(passwordRegex)
    }

}