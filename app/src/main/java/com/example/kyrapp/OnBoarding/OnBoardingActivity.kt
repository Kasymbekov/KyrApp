package com.example.kyrapp.OnBoarding

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.kyrapp.MainActivity
import com.example.kyrapp.R
import com.example.kyrapp.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private lateinit var adapter: OnBoardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf(
            Triple(R.drawable.etn, "Салам Алееейкум", "Сейчас будет жарко"),
            Triple(R.drawable.aktan_ak, "Я не киргиз, я кыргыз! ", "ИИИИИИУУ🤙🤙🤙"),
            Triple(R.drawable.aygen, "В конце подарок!!", "Жду дизайна, ваываоы ваывавыа ыва ываевапавпвпвапрвап ")
        )

        adapter = OnBoardingAdapter(items)
        binding.viewP.adapter = adapter

        setupIndicators(items.size)
        setCurrentIndicator(0)

        binding.viewP.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })

        binding.nextBt.setOnClickListener {
            val currentItem = binding.viewP.currentItem
            if (currentItem < items.size - 1) {
                binding.viewP.currentItem = currentItem + 1
            }
            if(currentItem==items.size-1){
                binding.nextBt.text = R.string.onboardLastPage.toString()
            }
            if (currentItem!=items.size-1){
                binding.nextBt.text = R.string.onboardNextPage.toString()
            }
            else {
                val intent = Intent(this@OnBoardingActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun setupIndicators(count: Int) {
        val indicators = arrayOfNulls<ImageView>(count)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(this)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.indicator_inactive
                    )
                )
                it.layoutParams = layoutParams
                binding.indicatorLayout.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = binding.indicatorLayout.childCount
        for (i in 0 until childCount) {
            val imageView = binding.indicatorLayout.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}
