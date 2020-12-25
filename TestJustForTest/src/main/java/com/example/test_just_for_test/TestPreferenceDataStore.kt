package com.example.test_just_for_test

import androidx.preference.PreferenceDataStore

class TestPreferenceDataStore: PreferenceDataStore() {

    companion object{
        private var instance: TestPreferenceDataStore? = null

        fun getInstance(): TestPreferenceDataStore{
            if (instance == null){
                instance = TestPreferenceDataStore()
            }
            return instance!!
        }

    }

    override fun putString(key: String?, value: String?) {
        super.putString(key, value)
    }

    override fun putStringSet(key: String?, values: MutableSet<String>?) {
        super.putStringSet(key, values)
    }

    override fun putInt(key: String?, value: Int) {
        super.putInt(key, value)
    }

    override fun putLong(key: String?, value: Long) {
        super.putLong(key, value)
    }

    override fun putFloat(key: String?, value: Float) {
        super.putFloat(key, value)
    }

    override fun putBoolean(key: String?, value: Boolean) {
        super.putBoolean(key, value)
    }

    override fun getString(key: String?, defValue: String?): String? {
        return super.getString(key, defValue)
    }

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String>? {
        return super.getStringSet(key, defValues)
    }

    override fun getInt(key: String?, defValue: Int): Int {
        return super.getInt(key, defValue)
    }

    override fun getLong(key: String?, defValue: Long): Long {
        return super.getLong(key, defValue)
    }

    override fun getFloat(key: String?, defValue: Float): Float {
        return super.getFloat(key, defValue)
    }

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return super.getBoolean(key, defValue)
    }
}