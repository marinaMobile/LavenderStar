package com.maroieqrwlk.unpi.white.ui.settings

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.core.content.edit
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import androidx.preference.get
import com.maroieqrwlk.unpi.*
import com.maroieqrwlk.unpi.white.*
import com.maroieqrwlk.unpi.white.ui.PresetActivity
import kotlin.math.min

class PreferencesFragment : PreferenceFragmentCompat() {
    private fun updateValues() {
        preferenceManager.sharedPreferences.let { prefs ->
            preferenceScreen.let {
                it.get<SwitchPreferenceCompat>("safe")!!.isChecked =
                    prefs.getBoolean(KEY_SAFE, true)
                it.get<SwitchPreferenceCompat>("chord")!!.isChecked =
                    prefs.getBoolean(KEY_CHORD, true)
                it.get<SwitchPreferenceCompat>("invert")!!.isChecked =
                    prefs.getBoolean(KEY_INVERT, false)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        updateValues()
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_game, rootKey)
        preferenceManager.sharedPreferencesName = PREFS_NAME

        preferenceScreen.get<Preference>("safe")!!.setOnPreferenceChangeListener { _, value ->
            preferenceManager.sharedPreferences.edit {
                putBoolean(KEY_SAFE, value as Boolean)
            }
            true
        }

        preferenceScreen.get<Preference>("chord")!!.setOnPreferenceChangeListener { _, value ->
            preferenceManager.sharedPreferences.edit {
                putBoolean(KEY_CHORD, value as Boolean)
            }
            true
        }

        preferenceScreen.get<Preference>("invert")!!.setOnPreferenceChangeListener { _, value ->
            preferenceManager.sharedPreferences.edit {
                putBoolean(KEY_INVERT, value as Boolean)
            }
            true
        }
    }

    @SuppressLint("ResourceType")
    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        preferenceManager.sharedPreferences.let { prefs ->
            when (preference.key) {
                "set_preset" -> {
                    val intent = Intent(context, PresetActivity::class.java)
                    startActivity(intent)
                }
                else -> {return false}
            }

        }

        return true
    }
}
