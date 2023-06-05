package com.example.fliemanager.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fliemanager.Global
import com.example.fliemanager.R
import com.example.fliemanager.manager.FileManager
import com.example.fliemanager.ui.PictureActivity
import com.example.fliemanager.ui.adapter.SinglePageAdapter
import com.example.fliemanager.viewmodel.SinglePageViewModel

//更新，直接使用fragment代替viewpager来显示文件
class SinglePageFragment: Fragment() {

    val TAG:String = "FileManager_SinglePageFragment2"
    lateinit var recyclerView: RecyclerView
    val adapter = SinglePageAdapter()
    //记录创建时的路径
    lateinit var pathCreated:String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pathCreated = FileManager.getPathNow()

        recyclerView = view.findViewById<RecyclerView>(R.id.fp_recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter.data = FileManager.getPathDataNow()

    }

    override fun onDestroy() {
        super.onDestroy()
        FileManager.deleteLastPath()
    }
}