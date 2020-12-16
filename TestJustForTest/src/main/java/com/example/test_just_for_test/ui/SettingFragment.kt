package com.example.test_just_for_test.ui

import android.os.Bundle
import android.util.Log
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.test_just_for_test.R
import com.example.test_just_for_test.TestPreferenceDataStore

class SettingFragment: PreferenceFragmentCompat(), PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_screen, rootKey)

        var preference = findPreference<Preference>("feedback_preference1")

        preference?.setOnPreferenceClickListener(object : Preference.OnPreferenceClickListener {
            override fun onPreferenceClick(preference: Preference?): Boolean {
                var a =
                    TestPreferenceDataStore.getInstance().getBoolean("switch_test1", false)
                Log.i("SettingFragment", "onPreferenceClick: the a is feedback_preference1 and the value is " + a)
                return true
            }

        })

    }

    override fun onPreferenceStartFragment(
        caller: PreferenceFragmentCompat?,
        pref: Preference?
    ): Boolean {

        return false
    }
}