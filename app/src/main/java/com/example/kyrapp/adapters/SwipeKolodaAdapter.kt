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

class SwipeKolodaAdapter(private val context: Context, private val items: List<Triple<Int, String, String>>) : BaseAdapter() {
    lateinit var mediaPlayer: MediaPlayer

    // Возвращаем произвольное большое значение, чтобы создать ощущение бесконечной прокрутки
    override fun getCount(): Int {
        return Integer.MAX_VALUE
    }

    override fun getItem(position: Int): Any {
        // Используем модуль для перезапуска индекса
        return items[position % items.size]
    }

    override fun getItemId(position: Int): Long {
        return (position % items.size).toLong()
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

        // Используем модуль для бесконечной прокрутки
        val actualPosition = position % items.size
        val (imageResId, text, imageText) = items[actualPosition]

        // Инициализация MediaPlayer и воспроизведение звука
        viewHolder.imageView.setImageResource(imageResId)
        viewHolder.textView.text = text
        viewHolder.imagetext.text = imageText

        viewHolder.imageView.setOnClickListener {
            if (::mediaPlayer.isInitialized) {
                mediaPlayer.release()
            }
            mediaPlayer = MediaPlayer.create(context, R.raw.a)
            mediaPlayer.start()
        }

        return view
    }

    private class ViewHolder(view: View) {
        val imageView: ImageView = view.findViewById(R.id.alphabetImage)
        val textView: TextView = view.findViewById(R.id.alphabetText)
        val imagetext: TextView = view.findViewById(R.id.imageAlphabetText)
    }
}


