package com.example.fliemanager.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Environment
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
            0 -> doBusiness()
            1 ->{}
            else -> {}
        }


    }

    fun doBusiness(){
        var adapter = SinglePageAdapter()
        view?.findViewById<RecyclerView>(R.id.fp_recyclerview)?.adapter = adapter
        view?.findViewById<RecyclerView>(R.id.fp_recyclerview)?.layoutManager = LinearLayoutManager(context)

        viewmodel.FilePathLiveData.observe(this, Observer {
            adapter.data = it
        })
        adapter.choosePath.observe(this, Observer {
            if (it.isDirectory){
                viewmodel.refreshPathData(it.name)
            }
            when(it.type){
                Global.fileType.jpg ->{
                    var intent = Intent(context, PictureActivity::class.java)
                    intent.putExtra(Global.intentTag.jpgPath, it.path)
                    startActivity(intent)
                }
            }

        })

        viewmodel.startGetData()
    }

}