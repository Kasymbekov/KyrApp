package com.example.kyrapp.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LetterDao {
    @Query("SELECT * FROM letter")
    fun getAll(): List<Letter>

    @Query("SELECT * FROM letter WHERE id IN (:letterIds)")
    fun loadAllByIds(letterIds: IntArray): List<Letter>

    @Insert
    fun insertAll(vararg letters: Letter)

    @Delete
    fun delete(letter: Letter)
}