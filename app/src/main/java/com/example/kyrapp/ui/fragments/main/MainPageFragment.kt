package com.example.kyrapp.ui.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kyrapp.R
import com.example.kyrapp.adapters.ItemAdapter
import com.example.kyrapp.data.source.local.AppDatabase
import com.example.kyrapp.data.source.local.Letter
import com.example.kyrapp.databinding.FragmentMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPageFragment : Fragment() {

    private lateinit var itemAdapter: ItemAdapter
    private lateinit var mainPageViewModel: MainPageViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemAdapter = ItemAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView2)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = itemAdapter

        mainPageViewModel = ViewModelProvider(this)[MainPageViewModel::class.java]
        mainPageViewModel.items.observe(viewLifecycleOwner, Observer {
            itemAdapter.setItems(it)
        })

        // just to adding items into the db
        val db = AppDatabase.getDatabase(requireContext())
        GlobalScope.launch {
        //db.letterDao().insert(Letter( letter = "А", word = "Алма", drawableId = 2131230841))
        }

    }

}