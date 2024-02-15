package com.example.demoprojectandroid.Helper

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

object Helper {

    fun formatDateTime(inputDate: String?, inputFormat: String?, outputFormat: String?): String? {
        val sdf = SimpleDateFormat(inputFormat)
        var formattedDate = ""
        try {
            val date: Date = sdf.parse(inputDate)
            val outputSdf = SimpleDateFormat(outputFormat)
            formattedDate = outputSdf.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formattedDate
    }

    fun hideKeyboard(activity: Activity, editText: EditText) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }

    fun isKeyboardOpen(context: Context): Boolean {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return imm.isAcceptingText
    }
}