package com.example.demoprojectandroid.CommonUi

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.example.demoprojectandroid.R
import com.example.demoprojectandroid.databinding.ActivityCommonUiactivityBinding


class CommonUIActivity : AppCompatActivity() {

    lateinit var binding: ActivityCommonUiactivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonUiactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PopupMenu()
        CLickableSpann()

        NavigationMenu(this, binding.drawerListView, binding.btnpop, binding.myDrawerLayout).initDrawer()
    }

    private fun CLickableSpann() {
        binding.text.setText(setSpanString("Don't have an account?", " Sign up now!"))

    }

    private fun PopupMenu() {
        binding.popupButton.setOnClickListener(View.OnClickListener {

            val popupMenu = PopupMenu(this, binding.popupButton)  // Use 'this' for activity or 'requireContext()' for fragment
            popupMenu.menuInflater.inflate(R.menu.menu_popup, popupMenu.menu)

            // Optional: Set a listener for item clicks
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.java -> {

                        true
                    }

                    R.id.kotlin -> {

                        true
                    }

                    R.id.react_native -> {

                        true
                    }

                    R.id.android -> {

                        true
                    }

                    else -> false
                }
            }

            popupMenu.show()
        })
    }

    private fun setSpanString(name: String, invitetext: String): SpannableString {
        val ss = SpannableString("Don't have an account? Sign up now!")

        val clickableSpan: ClickableSpan = object : ClickableSpan() {

            override fun onClick(textView: View) {

                startActivity(Intent(this@CommonUIActivity,CommonUIActivity::class.java))
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.setColor(Color.RED)
                ds.isUnderlineText = false
            }
        }
        //set clickable
        ss.setSpan(clickableSpan, 23, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        //set bold
        val boldSpan = StyleSpan(Typeface.BOLD)
        ss.setSpan(boldSpan, 23, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)


        binding.text.movementMethod = LinkMovementMethod.getInstance()
        binding.text.highlightColor = Color.TRANSPARENT
        return ss
    }
}