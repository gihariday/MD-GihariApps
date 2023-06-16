package com.dicoding.c23_pc713.gihariapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.c23_pc713.gihariapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.cvChat.setOnClickListener {
            intent = Intent(this, ChatActivity::class.java).also { startActivity(it) }
        }
        binding.cvGallery.setOnClickListener {
            intent = Intent(this, GalleryActivity::class.java).also { startActivity(it) }
        }
        binding.cvArticles.setOnClickListener {
            intent = Intent(this, ArticlesActivity::class.java).also { startActivity(it) }
        }
        binding.cvSettings.setOnClickListener {
            intent = Intent(this, SettingsActivity::class.java).also { startActivity(it) }
        }
    }
}