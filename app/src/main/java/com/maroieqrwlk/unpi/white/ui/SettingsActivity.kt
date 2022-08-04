package com.maroieqrwlk.unpi.white.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maroieqrwlk.unpi.R
import com.maroieqrwlk.unpi.databinding.ActivitySettingsBinding
import com.maroieqrwlk.unpi.white.ui.settings.PreferencesFragment

class SettingsActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, PreferencesFragment()).commit()
    }
}