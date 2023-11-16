package com.example.fliemanager.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fliemanager.Global
import com.example.fliemanager.R
import com.example.fliemanager.databinding.FragmentPagesBinding
import com.example.fliemanager.manager.FileManager
import com.example.fliemanager.ui.PictureActivity
import com.example.fliemanager.ui.adapter.SinglePageAdapter
import com.example.fliemanager.viewmodel.SinglePageViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//更新，直接使用fragment代替viewpager来显示文件
class SinglePageFragment(var rootFragment: Boolean = false): Fragment() {

    val TAG:String = "FileManager_SinglePageFragment"
    val adapter = SinglePageAdapter()
    //记录创建时的路径
    lateinit var pathCreated:String
    lateinit var binding: FragmentPagesBinding

    val dataChangeNotification: MutableLiveData<Boolean> = MutableLiveData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPagesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        pathCreated = FileManager.getPathNow()

        binding.fpRecyclerview.adapter = adapter
        binding.fpRecyclerview.layoutManager = LinearLayoutManager(context)

        dataChangeNotification.observe(this, Observer{
            adapter.notifyDataSetChanged()
        })

        GlobalScope.launch {
            //异步加载数据，防止卡顿
            adapter.data = FileManager.getPathDataNow()
            dataChangeNotification.postValue(true)
        }.start()



    }

    override fun onDestroy() {
        super.onDestroy()
        FileManager.deleteLastPath()
        if (rootFragment){
            FileManager.returnToRootPath()
        }
    }
}