package com.example.kyrapp.adapters

import android.content.Context
import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.kyrapp.R

class SwipeKolodaAdapter(private val context: Context, private val items: List<Pair<Int, String>>) : BaseAdapter() {
    private lateinit var mediaPlayer: MediaPlayer
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_koloda, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val (imageResId, text) = items[position]

        viewHolder.imageView.setImageResource(imageResId)
        viewHolder.textView.text = text

        // play audio of the letter
        viewHolder.imageView.setOnClickListener {
            mediaPlayer = MediaPlayer.create(context, R.raw.a)
            mediaPlayer.start()
        }
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
        return view
    }

    private class ViewHolder(view: View) {
        val imageView: ImageView = view.findViewById(R.id.alphabetImage)
        val textView: TextView = view.findViewById(R.id.alphabetText)
    }

}

