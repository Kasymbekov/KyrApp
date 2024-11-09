package com.example.kyrapp.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LetterDao {
    @Query("SELECT * FROM letters_table")
    fun getAll(): LiveData<List<Letter>>

    @Query("SELECT * FROM letters_table WHERE id IN (:letterIds)")
    fun loadAllByIds(letterIds: IntArray): List<Letter>
    @Insert
    fun insert(letter: Letter)

    @Insert
    fun insertAll(vararg letters: Letter)

    @Delete
    fun delete(letter: Letter)
}