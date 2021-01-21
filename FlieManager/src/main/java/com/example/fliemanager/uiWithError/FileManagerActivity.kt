package com.example.fliemanager.uiWithError

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.fliemanager.uiWithError.adapter.FileManagerViewPagerAdapter
import com.example.fliemanager.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FileManagerActivity : AppCompatActivity() {
    private val TAG = "FileManagerActivity"
    private var list: List<String> = arrayListOf("文件","test")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_manager_with_error)

        if(PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }
    }

    override fun onStart() {
        super.onStart()



        var viewPagerAdapter = FileManagerViewPagerAdapter(this, list)
        findViewById<ViewPager2>(R.id.fileManager_ViewPager).adapter = viewPagerAdapter

        TabLayoutMediator(findViewById<TabLayout>(R.id.fileManager_TableLayout), findViewById<ViewPager2>(R.id.fileManager_ViewPager),
            object :TabLayoutMediator.TabConfigurationStrategy{
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    tab.text = list[position]
                }
            }).attach()

//        TabLayoutMediator(
//            fileManager_TableLayout,
//            fileManager_ViewPager
//        ) { tab, position -> tab.text = list[position] }.attach()
    }

    override fun onPause() {
        super.onPause()
    }
}
