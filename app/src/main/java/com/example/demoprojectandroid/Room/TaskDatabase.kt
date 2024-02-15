package com.example.demoprojectandroid.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Contacts::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun  taskdao() : ContactsDao

    companion object{

            //inform all thread about object creation   @Volatile
            @Volatile
            private  var INSTANCE : ContactDatabase? = null

        fun getDatabase(contex: Context) : ContactDatabase {
            if(INSTANCE == null){
                //sycnhronized lock for not create duplicate instance
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(contex.applicationContext, ContactDatabase::class.java, "ContactDB").build()
                }
            }
            return INSTANCE!!
        }
    }

}