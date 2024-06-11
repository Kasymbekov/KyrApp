package com.example.kyrapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kyrapp.R
//import com.example.kyrapp.adapters.SwipeKolodaAdapter
//import com.yalantis.library.Koloda

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AlphabetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlphabetFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alphabet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val kolodaView = view.findViewById<Koloda>(R.id.koloda)
//        val items = listOf(
//            Pair(R.drawable.alma, "А"),
//            Pair(R.drawable.balyk, "Б"),
//            Pair(R.drawable.vaza, "В")
//        )
//        val adapter = SwipeKolodaAdapter(requireContext(), items)
//        kolodaView.adapter = adapter
    }


}