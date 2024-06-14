package com.example.kyrapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kyrapp.R
import com.example.kyrapp.model.Item

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var items: List<Item> = listOf()

    fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lesson_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val lessonNum: TextView = itemView.findViewById(R.id.lessonNum)
        private val themeTitle: TextView = itemView.findViewById(R.id.lesson_theme_title)
        private val trainingPeriod: TextView = itemView.findViewById(R.id.training_period_tv)
        private val videoLectures: TextView = itemView.findViewById(R.id.video_lectures_tv)
        private val files: TextView = itemView.findViewById(R.id.files_tv)
        private val startLessonButton: Button = itemView.findViewById(R.id.start_lesson_bt)

        fun bind(item: Item) {
            lessonNum.text = item.lessonNum
            themeTitle.text = item.themeTitle
            trainingPeriod.text = item.trainingPeriod
            videoLectures.text = item.videoLectures
            files.text = item.files
            startLessonButton.setOnClickListener {
                // Обработка нажатия кнопки
            }
        }
    }
}
