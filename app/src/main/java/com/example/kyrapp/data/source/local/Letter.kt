package com.example.kyrapp.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "letters_table")
data class Letter(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "letter") val letter: String,
    @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "drawableId") val drawableId: Int
)
