package com.example.newzapp.DataBase

import android.accessibilityservice.GestureDescription
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "content_entity", indices = [Index(value = ["title","description"], unique = true)])
data class Entity (
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val title: String?,
    val description: String?,
    val image_URL : String?
    )