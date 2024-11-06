package com.example.kyrapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.trusted.ScreenOrientation
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.room.Room
import com.example.kyrapp.data.source.local.AppDatabase
import com.example.kyrapp.data.source.local.Letter
import com.example.kyrapp.databinding.ActivityMainScreenBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navigation = Navigation.findNavController(this, R.id.fragment_host_main)
        NavigationUI.setupWithNavController(binding.bottomMenu, navigation)
        if (savedInstanceState == null) {
            val navController = findNavController(R.id.fragment_host_main)
            navController.navigate(R.id.mainFragment)
        }

        Toast.makeText(this, this.javaClass.name.toString(), Toast.LENGTH_LONG).show()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT // force portrait mode

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "db")
            .allowMainThreadQueries().fallbackToDestructiveMigration().build()
        GlobalScope.launch {

            //db.letterDao().insert(Letter( 1, "a", "арык", "C://Program files"))
            val users: List<Letter> = db.letterDao().getAll()
            Log.e("nurs", "${users.size}")

        }
    }
}