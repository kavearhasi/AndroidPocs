package com.example.quotes.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import dagger.Provides

@Entity(tableName = "quote_table")
data class Quotes(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val author: String,
    val category: String,
    val quote: String,


)
