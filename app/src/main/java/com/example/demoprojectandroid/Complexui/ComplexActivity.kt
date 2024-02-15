package com.example.demoprojectandroid.Complexui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoprojectandroid.R
import com.example.demoprojectandroid.databinding.ActivityCommonUiactivityBinding
import com.example.demoprojectandroid.databinding.ActivityComplexBinding

class ComplexActivity : AppCompatActivity() {

    var mainlist: MutableList<String> = mutableListOf()
    var innerlist: MutableList<String> = mutableListOf()
    private lateinit var mainAdapter: MainAdapter
    lateinit var binding: ActivityComplexBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComplexBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setMainAdapter()
    }

    fun setMainAdapter() {


        // Fill mainlist with sample data
        mainlist.add("Item 1")
        mainlist.add("Item 2")
        mainlist.add("Item 3")

// Fill innerlist with sample data
        innerlist.add("Inner Item 1")
        innerlist.add("Inner Item 2")
        innerlist.add("Inner Item 3")



        mainAdapter = MainAdapter(this, mainlist, innerlist)
        val mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvmain.setLayoutManager(mLayoutManager)
        binding.rvmain.setNestedScrollingEnabled(false)
        binding.rvmain.setAdapter(mainAdapter)
    }
}