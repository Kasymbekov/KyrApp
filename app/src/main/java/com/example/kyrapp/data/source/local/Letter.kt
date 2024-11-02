package com.example.kyrapp.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Letter(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "letter") val letter: String,
    @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "path") val path: String
)
