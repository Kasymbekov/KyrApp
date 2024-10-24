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

        val items = listOf(
            Pair(R.drawable.alma, "А"),
            Pair(R.drawable.balyk, "Б"),
            Pair(R.drawable.vaza, "В")
        )
        val adapter = SwipeKolodaAdapter(requireContext(), items)
        binding.koloda.adapter = adapter
    }


}