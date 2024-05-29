package com.example.kyrapp.OnBoarding




import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kyrapp.databinding.ItemOnboardingContainerBinding

class OnBoardingAdapter(private val items: List<Triple<Int, String, String>>) :
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Triple<Int, String, String>) {
            binding.imageView.setImageResource(item.first)
            binding.titleTv.text = item.second
            binding.descTv.text = item.third
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val binding = ItemOnboardingContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnBoardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
