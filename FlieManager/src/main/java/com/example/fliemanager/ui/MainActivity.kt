package com.example.fliemanager.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fliemanager.Global
import com.example.fliemanager.R
import com.example.fliemanager.databinding.ActivityMainBinding
import com.example.fliemanager.manager.FileManager
import com.example.fliemanager.ui.adapter.PathAdapter
import com.example.fliemanager.ui.adapter.SinglePageAdapter
import com.example.fliemanager.ui.fragment.SinglePageFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity: AppCompatActivity() {

    private val TAG: String = "ProjectPractice_MainActivity"
    private val testLiveData: MutableLiveData<String> = MutableLiveData()
    private lateinit var binding: ActivityMainBinding

    private lateinit var listAdapter: SinglePageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //初始化 Filemanager
        FileManager.initialize()

        if(PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }

        //版本超过30则需要申请所有文件的管理权限
        if (Build.VERSION.SDK_INT >= 30){
            if(!Environment.isExternalStorageManager()){
//            getManageSpaceActivityIntent()
                var intent  = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 0)
            }
        }



        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        val pathViewAdapter = PathAdapter()
        listAdapter = SinglePageAdapter()

        binding.acRecyclerviewFilepath.layoutManager = layoutManager
        binding.acRecyclerviewFilepath.adapter = pathViewAdapter

        // 监听显示当前路径信息
        FileManager.pathNowLiveData.observe(this, Observer {
            pathViewAdapter.pathList = FileManager.getPathList(it)
            pathViewAdapter.notifyDataSetChanged()
        })


        binding.acRecyclerviewFilelist.adapter = listAdapter
        binding.acRecyclerviewFilelist.layoutManager = LinearLayoutManager(this)

        GlobalScope.launch {
            listAdapter.data = FileManager.getDataAtPath(Environment.getExternalStorageDirectory().absolutePath)
            testLiveData.postValue("true")
        }.start()


        //TODO 以后开启应用之后直接吊起指定路径信息
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .add(R.id.ac_framelayout, SinglePageFragment(true), "root")
            .addToBackStack("0")
            .commit()


        testLiveData.observe(this, Observer{
            listAdapter.notifyDataSetChanged()
        })
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