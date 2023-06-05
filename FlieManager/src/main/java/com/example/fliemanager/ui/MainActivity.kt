package com.example.fliemanager.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fliemanager.R
import com.example.fliemanager.manager.FileManager
import com.example.fliemanager.ui.adapter.PathAdapter
import com.example.fliemanager.ui.fragment.SinglePageFragment

class MainActivity: AppCompatActivity() {

    private val TAG: String = "ProjectPractice_MainActivity"
    private lateinit var pathView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }

        pathView = findViewById<RecyclerView>(R.id.ac_recyclerview_filepath)
        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        val pathViewAdapter = PathAdapter()

        pathView.layoutManager = layoutManager
        pathView.adapter = pathViewAdapter

        //监听显示当前路径信息
        FileManager.pathNowLiveData.observe(this, Observer {
            pathViewAdapter.pathList = FileManager.getPathList(it)
            pathViewAdapter.notifyDataSetChanged()
        })

        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .add(R.id.ac_framelayout, SinglePageFragment(), "root")
            .addToBackStack("0")
            .commit()

        Log.i(TAG, "onCreate: the path data now is " + FileManager.pathDataNow.value.toString())

        //初始化 Filemanager
        FileManager.initialize()

        FileManager.pathBeanLiveData.observe(this, Observer {
            if (it.doAdd){
                supportFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.ac_framelayout, SinglePageFragment(), null)
                    .addToBackStack("0")
                    .commit()
            }
        })

    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        return super.dispatchKeyEvent(event)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        Log.i(TAG, "onKeyDown: the keycode is ${event?.keyCode}")
        if (event?.keyCode == KeyEvent.KEYCODE_BACK){
//            Global.positionReturn = -1
//            if (FileManager.backUp()) return true
        }

        return super.onKeyDown(keyCode, event)
    }

}