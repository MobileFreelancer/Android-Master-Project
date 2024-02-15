package com.example.demoprojectandroid.Room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.demoprojectandroid.R
import com.example.demoprojectandroid.databinding.ActivityNotificationBinding
import com.example.demoprojectandroid.databinding.ActivityRoomBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomActivity : AppCompatActivity() {
    lateinit var binding: ActivityRoomBinding
    lateinit var database: ContactDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //create database object
        database = ContactDatabase.getDatabase(this)



        binding.buttoninsert.setOnClickListener(View.OnClickListener {
            GlobalScope.launch {
                database.taskdao().insertTask(Contacts(0, "Kushal P", "This is decription"))
            }
        })
        binding.buttonfetch.setOnClickListener(View.OnClickListener {
            database.taskdao().getAllContacts().observe(this, Observer {
                Toast.makeText(this, "" + it.toString(), Toast.LENGTH_SHORT).show()
            })
        })
        binding.buttondelete.setOnClickListener(View.OnClickListener {
            GlobalScope.launch {
                database.taskdao().delete()
            }

        })

    }
}