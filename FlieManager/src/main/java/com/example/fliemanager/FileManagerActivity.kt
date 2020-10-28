package com.example.fliemanager

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.example.fliemanager.Adapter.FileManagerViewPagerAdapter
import com.example.fliemanager.Manager.FileManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_file_manager.*

class FileManagerActivity : AppCompatActivity() {
    private val TAG = "FileManagerActivity"
    private var list: List<String> = arrayListOf("文件","test")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_manager)

        if(PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }


        var adapter = FileManagerViewPagerAdapter(this, list)
        fileManager_ViewPager.adapter = adapter

        fileManager_TableLayout

        //TODO 路径错误，待修正
        Log.i(TAG, "onCreate: the externalStorageState is " + Environment.getRootDirectory().absolutePath)
        FileManager.getInstance().getAll("./storage/emulated/0/")

        TabLayoutMediator(
            fileManager_TableLayout,
            fileManager_ViewPager
        ) { tab, position -> tab.text = list[position] }.attach()
    }
}
