package com.example.fliemanager.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.fliemanager.Global
import com.example.fliemanager.R
import com.example.fliemanager.manager.FileManager
import com.example.fliemanager.ui.adapter.PagesAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity: AppCompatActivity() {

    private val TAG: String = "ProjectPractice_MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }

        findViewById<ViewPager2>(R.id.ac_viewpager).adapter = PagesAdapter(this)

        TabLayoutMediator(findViewById(R.id.ac_tablayout), findViewById(R.id.ac_viewpager), object: TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                tab.text = Global.pages[position]
            }
        }).attach()
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        return super.dispatchKeyEvent(event)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        Log.i(TAG, "onKeyDown: the keycode is ${event?.keyCode}")
        if (event?.keyCode == KeyEvent.KEYCODE_BACK){
            Global.positionReturn = -1
            if (FileManager.backUp()) return true

        }
        return super.onKeyDown(keyCode, event)
    }
}