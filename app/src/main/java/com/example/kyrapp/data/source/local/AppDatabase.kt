package com.example.kyrapp.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Letter::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun letterDao(): LetterDao

    companion object {
        // using singleton pattern
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // get the instance of the db
        fun getDatabase(context: Context): AppDatabase {
            // if the instance is null create a new one
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}