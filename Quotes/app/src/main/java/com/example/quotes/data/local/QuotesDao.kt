package com.example.quotes.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.DeleteColumn
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quotes.domain.models.Quotes


@Dao
interface QuotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quote:Quotes)

    @Query("select * from quote_table ")
    suspend fun getAllQuotes(): List<Quotes>

    @Query("DELETE FROM quote_table")
    suspend fun deleteAllQuotes()





}