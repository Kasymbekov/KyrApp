package com.example.kyrapp.ui.fragments.alphabet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.kyrapp.adapters.SwipeKolodaAdapter
import com.example.kyrapp.databinding.FragmentAlphabetBinding

class AlphabetPageFragment : Fragment() {
    private lateinit var binding: FragmentAlphabetBinding
    private lateinit var itemViewModel: AlphabetPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlphabetBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = mutableListOf<Triple<Int, String, String>>()
        itemViewModel = ViewModelProvider(this)[AlphabetPageViewModel::class.java]
        itemViewModel.letters.observe(viewLifecycleOwner) { letters ->
            items.addAll(letters.map { Triple(it.drawableId, it.letter, it.word) })
            binding.koloda.adapter = SwipeKolodaAdapter(requireContext(), items)
        }
    }
}