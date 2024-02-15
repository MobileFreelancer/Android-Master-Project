package com.example.demoprojectandroid.CommonUi

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.demoprojectandroid.R
import java.util.*

class NavigationMenu(private val activity: Activity, private val drawerListView: ListView, private val ivDrawer: ImageView, private val drawer_layout: DrawerLayout) {


    private var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    private var navigationDrawerAdapter: DrawerAdapter? = null
    private var listHeaderView: View? = null
    private var ishead = false

    companion object {
        var list: List<String>? = null
        var imageList: List<Int>? = null
    }

    //TODO: set Drawer Data
    fun initDrawer() {
        list = listOf("Coaches Home Page", "Team Selection Page", "Player Registration", "Reactivate Player", "Email Players", "FAQ", "Contact Us", "Logout")
        imageList = listOf(R.drawable.baseline_menu_24, R.drawable.ic_launcher_background, R.drawable.baseline_menu_24,
                           R.drawable.baseline_menu_24, R.drawable.ic_launcher_background, R.drawable.baseline_menu_24,
                           R.drawable.baseline_menu_24, R.drawable.ic_launcher_background, R.drawable.baseline_menu_24,)

        val inflater = activity.layoutInflater
        listHeaderView = inflater.inflate(R.layout.layout_navigation_header, null, false)

        if (!ishead) {
            drawerListView.addHeaderView(listHeaderView)
            ishead = true
        }

        navigationDrawerAdapter = DrawerAdapter(activity, list!!)
        drawerListView.adapter = navigationDrawerAdapter
        ivDrawer.setOnClickListener {
            drawer_layout.openDrawer(Gravity.LEFT)
        }

        actionBarDrawerToggle = object : ActionBarDrawerToggle(activity, drawer_layout, 0, 0) {
            override fun onDrawerClosed(view: View) {
                super.onDrawerClosed(view)
                activity.invalidateOptionsMenu() // creates call to onPrepareOptionsMenu()
            }

            /* Called when a drawer has settled in a completely open state. */
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                activity.invalidateOptionsMenu() // creates call to onPrepareOptionsMenu()
            }
        }

        drawerListView.setOnItemClickListener { parent, view, position, id ->
            selectItemFragment(position - 1)
        }
    }

    private fun selectItemFragment(position: Int) {
        drawerListView.setItemChecked(position, true)
        selectLocalFragment(position)
        Handler().postDelayed({
                                  drawer_layout.closeDrawer(drawerListView)
                              }, 200)
    }

    private fun selectLocalFragment(position: Int) {

    }
}