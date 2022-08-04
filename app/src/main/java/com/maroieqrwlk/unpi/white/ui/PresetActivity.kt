package com.maroieqrwlk.unpi.white.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maroieqrwlk.unpi.R
import com.maroieqrwlk.unpi.databinding.ActivityPresetBinding
import com.maroieqrwlk.unpi.white.ui.settings.PresetSettingsFragment

class PresetActivity: AppCompatActivity() {

    private lateinit var binding: ActivityPresetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPresetBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container2, PresetSettingsFragment()).commit()
    }
}