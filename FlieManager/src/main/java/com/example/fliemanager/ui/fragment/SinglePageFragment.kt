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

class SinglePageFragment(var position: Int): Fragment() {

    private val TAG: String = "FileManager_SinglePageFragment"
    var adapter = SinglePageAdapter()

    private val viewmodel by lazy {
        ViewModelProvider.NewInstanceFactory().create(SinglePageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(position){
            0 -> filePathBusiness()
            else -> {}
        }


    }

    fun filePathBusiness(){
        view?.findViewById<RecyclerView>(R.id.fp_recyclerview)?.adapter = adapter
        view?.findViewById<RecyclerView>(R.id.fp_recyclerview)?.layoutManager = LinearLayoutManager(context)

        viewmodel.FilePathLiveData.observe(this, Observer {
            adapter.data = it
        })
        adapter.choosePath.observe(this, Observer {
            if (it.nameBean.isDirectory){
                viewmodel.refreshPathData(it.nameBean.name)
            }
            when(it.nameBean.type){
                Global.fileType.jpg, Global.fileType.png ->{
                    var intent = Intent(context, PictureActivity::class.java)
                    intent.putExtra(Global.intentTag.jpgPath, it.nameBean.path)
                    intent.putExtra(Global.intentTag.clickPosition, it.position)
//                    startActivity(intent)
                    startActivityForResult(intent, 10)
                }
            }

        })

        viewmodel.startGetData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.getIntExtra("test_for_test", -1)?.let {
            Global.positionReturn = it
            adapter.notifyDataSetChanged()
            view?.findViewById<RecyclerView>(R.id.fp_recyclerview)?.scrollToPosition(it)
        }
    }
}