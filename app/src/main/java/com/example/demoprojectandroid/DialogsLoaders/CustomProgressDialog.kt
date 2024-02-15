package com.example.demoprojectandroid.DialogsLoaders

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.example.demoprojectandroid.R

class CustomProgressDialog private constructor(private val context: Context) {

    private var dialogView: Dialog? = null

    companion object {
        private var customProgressDialog: CustomProgressDialog? = null

        fun getCustomProgressDialog(context: Context): CustomProgressDialog {
            if (customProgressDialog == null) {
                customProgressDialog = CustomProgressDialog(context)
            }
            return customProgressDialog!!
        }
    }

    fun showCustomDialog() {
        dialogView = Dialog(context)
        dialogView?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogView?.setContentView(R.layout.layout_progress_dialog) // Replace with your layout file
        dialogView?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialogView?.show()
    }

    fun isShowing(): Boolean {
        return try {
            dialogView?.isShowing == true
        } catch (e: Exception) {
            false
        }
    }

    fun dissmissDialog() {
        try {
            if (dialogView?.isShowing == true) {
                dialogView?.dismiss()
            }
        } catch (e: Exception) {
            // Handle exception
        }
    }
}