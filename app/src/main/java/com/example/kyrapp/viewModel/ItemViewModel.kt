package com.example.kyrapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kyrapp.model.Item

class ItemViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    init {
        loadItems()
    }

    private fun loadItems() {
        val itemList = listOf(
            Item("Урок 1", "Изучение алфавита", "1 месяц", "5 видео", "10 файлов"),
            Item("Урок 2", "Изучение слов", "1 месяц", "5 видео", "10 файлов"),
            Item("Урок 3", "Составление предложений", "1 месяц", "5 видео", "10 файлов"),
            Item("Урок 4", "Фотки", "2 месяца", "20 видео", "4 файла")
        )
        _items.value = itemList
    }
}
