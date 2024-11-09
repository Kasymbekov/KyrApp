package com.example.kyrapp.ui.fragments.alphabet

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.kyrapp.data.model.Lesson
import com.example.kyrapp.data.source.local.AppDatabase
import com.example.kyrapp.data.source.local.Letter

class AlphabetPageViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application.applicationContext)
    val letters: LiveData<List<Letter>> = db.letterDao().getAll()
}