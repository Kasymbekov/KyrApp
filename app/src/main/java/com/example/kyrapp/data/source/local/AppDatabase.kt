package com.example.kyrapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Letter::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun letterDao(): LetterDao
}