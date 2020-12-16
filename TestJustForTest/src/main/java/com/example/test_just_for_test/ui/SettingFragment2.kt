package com.example.test_just_for_test.ui

import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.test_just_for_test.R

class SettingFragment2: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_screen_2, rootKey)
        val sharePreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        Log.i("TestJustForTest_SettingFragment2", "onCreatePreferences: the switch is " + sharePreferences.getBoolean("preference2_switchPreference1", false))
    }
}