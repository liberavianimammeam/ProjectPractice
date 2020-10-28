package com.example.fliemanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fliemanager.Adapter.FileManagerRecyclerViewAdapter
import com.example.fliemanager.Manager.FileManager
import kotlinx.android.synthetic.main.file_manager_fragment.view.*

class FileManagerFragment(list: List<String>, position: Int) : Fragment() {

    private lateinit var list: List<String>
    private var position: Int? = null

    companion object{

    }

    init {
        this.list = list
        this.position = position
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.file_manager_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var adapter = FileManagerRecyclerViewAdapter()

        view.fileManager_RecyclerView.adapter = adapter

        when(position){

            0->{

            }
            1->{

            }
        }

    }
}