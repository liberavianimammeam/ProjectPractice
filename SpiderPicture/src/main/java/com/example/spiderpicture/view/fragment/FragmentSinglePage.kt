package com.example.spiderpicture.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spiderpicture.Global
import com.example.spiderpicture.R
import com.example.spiderpicture.model.FragmentSinglePageViewModel
import com.example.spiderpicture.network.IHttpServer
import com.example.spiderpicture.util.RequestUtil
import com.example.spiderpicture.view.adapter.SinglePageAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FragmentSinglePage(val position: Int): Fragment() {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: SinglePageAdapter = SinglePageAdapter()

    private val mViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(FragmentSinglePageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_single_page, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RequestUtil.init(requireContext())
        mRecyclerView = view.findViewById<RecyclerView>(R.id.sp_fragment_single_page_rv)
        mRecyclerView?.adapter = mAdapter
        mRecyclerView?.layoutManager = LinearLayoutManager(requireContext())

        GlobalScope.launch(Dispatchers.Main) {
            var data = mViewModel.requestDataInPosition(position)
            mAdapter.data = data
            mAdapter.notifyDataSetChanged()
            //TODO 这里是自己使用 volley 请求网络图片数据，不过使用 glid 加载图片不需要此步骤
//            RequestUtil.requestCoverImageListData(data)
        }

//        RequestUtil.listBeanData.observe(this@FragmentSinglePage, Observer{
//            mAdapter.data = it
//            mAdapter.notifyDataSetChanged()
//        })
    }
}