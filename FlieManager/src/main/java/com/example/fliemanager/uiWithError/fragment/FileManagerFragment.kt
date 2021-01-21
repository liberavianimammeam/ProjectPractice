package com.example.fliemanager.uiWithError.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fliemanager.uiWithError.adapter.FileManagerRecyclerViewAdapter
import com.example.fliemanager.R

class FileManagerFragment(val list: List<String>, val position: Int) : Fragment() {
    private val TAG: String = "FileManager_FileManagerFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.file_manager_fragment_with_error, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var adapter = FileManagerRecyclerViewAdapter()

        view.findViewById<RecyclerView>(R.id.fileManager_RecyclerView).adapter = adapter
        view.findViewById<RecyclerView>(R.id.fileManager_RecyclerView).layoutManager = LinearLayoutManager(context)

        when(position){

            0->{
//                Log.i(TAG, "onCreate: the externalStorageState is " + Environment.getRootDirectory().absolutePath)
//                if (Global.filePositionNow == null){
//                    Global.filePositionNow = Environment.getExternalStorageDirectory().absolutePath
//                }
//                adapter.data = FileManager.getAll(Global.filePositionNow!!)
//                adapter.notifyDataSetChanged()
            }
            1->{

            }
        }

    }
}