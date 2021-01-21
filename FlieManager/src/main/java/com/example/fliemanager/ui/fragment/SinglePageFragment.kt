package com.example.fliemanager.ui.fragment

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fliemanager.R
import com.example.fliemanager.manager.FileManager
import com.example.fliemanager.ui.adapter.SinglePageAdapter

class SinglePageFragment(var position: Int): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var adapter = SinglePageAdapter()

        view.findViewById<RecyclerView>(R.id.fp_recyclerview).adapter = adapter
        view.findViewById<RecyclerView>(R.id.fp_recyclerview).layoutManager = LinearLayoutManager(context)


        adapter.data = FileManager.getAll(Environment.getExternalStorageDirectory().absolutePath)


    }
}