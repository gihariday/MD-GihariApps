package com.dicoding.c23_pc713.gihariapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.c23_pc713.gihariapp.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Set onClickListener for back button
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}