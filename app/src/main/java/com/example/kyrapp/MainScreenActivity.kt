package com.example.kyrapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.kyrapp.databinding.ActivityMainScreenBinding

class MainScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navigation = Navigation.findNavController(this,R.id.fragment_host_main)
        NavigationUI.setupWithNavController(binding.bottomMenu,navigation)
        if (savedInstanceState == null) {
            val navController = findNavController(R.id.fragment_host_main)
            navController.navigate(R.id.mainFragment)
        }
        Toast.makeText(this, this.javaClass.name.toString(), Toast.LENGTH_LONG).show()
    }
}