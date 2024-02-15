package com.example.demoprojectandroid.Localization

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.preference.PreferenceManager
import java.util.Locale

object LocaleHelper {
    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"

    fun setLocale(context: Context, language: String) {
        persist(context, language)
        updateResources(context, language)
    }

    fun getLocale(context: Context): Locale {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val lang = preferences.getString(SELECTED_LANGUAGE, "")
        return if (lang.isNullOrEmpty()) Locale.getDefault() else Locale(lang!!)
    }

    private fun persist(context: Context, language: String) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        preferences.edit().putString(SELECTED_LANGUAGE, language).apply()
    }

    private fun updateResources(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale)
        } else {
            config.locale = locale
        }

        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }
}