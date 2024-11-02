package com.example.kyrapp.ui.fragments

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kyrapp.R
import com.example.kyrapp.adapters.SwipeKolodaAdapter
import com.example.kyrapp.databinding.FragmentAlphabetBinding
import com.yalantis.library.Koloda

class AlphabetFragment : Fragment() {
    private lateinit var binding: FragmentAlphabetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlphabetBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val kolodaView = view.findViewById<Koloda>(R.id.koloda)

        // Загружаем строки и изображения из ресурсов
        val letters = resources.getStringArray(R.array.letters)
        val images = resources.obtainTypedArray(R.array.images)
        val imageText = resources.getStringArray(R.array.imageText)

        // Создаем список Triple для изображений, букв и дополнительных текстов
        val items = mutableListOf<Triple<Int, String, String>>()
        for (i in letters.indices) {
            val imageRes = images.getResourceId(i, -1)
            items.add(Triple(imageRes, letters[i], imageText[i]))
        }
        images.recycle() // Освобождаем массив изображений

        val adapter = SwipeKolodaAdapter(requireContext(), items)
        binding.koloda.adapter = adapter
    }


}