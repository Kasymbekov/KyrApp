package com.example.kyrapp.ui.fragments

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
import com.example.kyrapp.viewModel.ItemViewModel


class LessonsFragment : Fragment() {

    private lateinit var itemViewModel: ItemViewModel
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lessons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemAdapter = ItemAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvLesson)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = itemAdapter

        itemViewModel = ViewModelProvider(this)[ItemViewModel::class.java]
        itemViewModel.items.observe(viewLifecycleOwner, Observer {
            itemAdapter.setItems(it)
        })
    }
}
