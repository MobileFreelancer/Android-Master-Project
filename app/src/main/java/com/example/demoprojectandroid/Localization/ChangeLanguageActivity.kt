package com.example.demoprojectandroid.Localization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.demoprojectandroid.R

class ChangeLanguageActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_language)


    }



    fun changelanguage(view: View) {

        changeAppLanguage("hi");
    }

    fun changetoenglish(view: View) {
        changeAppLanguage("en");
    }

    private fun changeAppLanguage(languageCode: String) {
        LocaleHelper.setLocale(this, languageCode)

        // Restart the activity to apply the changes
        val refresh = Intent(this, ChangeLanguageActivity::class.java)
        startActivity(refresh)
        finish()
    }
}