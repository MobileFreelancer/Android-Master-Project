package com.example.demoprojectandroid.Room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Contacts")
data class Contacts(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val title: String,
        val description: String)


@Entity(tableName = "Users")
data class Users(
        @PrimaryKey val userId: Long,
        val userName: String,
        val email: String)
