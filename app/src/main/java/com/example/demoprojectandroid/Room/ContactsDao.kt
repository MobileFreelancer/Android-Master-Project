package com.example.demoprojectandroid.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface ContactsDao {

    @Insert
    suspend fun insertTask(contacts: Contacts)

    @Update
    suspend fun update(contacts: Contacts)

    @Query("SELECT * FROM Contacts")
    fun getAllContacts(): LiveData<List<Contacts>>

    @Query("DELETE FROM Contacts")
    fun delete()


}