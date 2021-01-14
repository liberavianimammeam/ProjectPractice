package com.example.spiderpicture.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
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

class FragmentSinglePage(val position: Int): Fragment(), View.OnClickListener {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: SinglePageAdapter = SinglePageAdapter()
    private val TAG: String = "SpiderPicture_FragmentSinglePage"
    private var page: Int = 1
    private var maxPage: Int? = null

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

        view.findViewById<Button>(R.id.sp_fragment_single_page_button_next).setOnClickListener(this)
        view.findViewById<Button>(R.id.sp_fragment_single_page_button_pre).setOnClickListener(this)

        GlobalScope.launch(Dispatchers.Main) {
            var data = mViewModel.requestDataInPosition(position, page = page)
            for (i in data){
                maxPage = i.maxPage
                if (maxPage != -1) break
            }
            mAdapter.data = data
            mAdapter.pagePosition = position
            mAdapter.notifyDataSetChanged()
            view.findViewById<TextView>(R.id.sp_fragment_single_page_tips).text = "$page/$maxPage"
            //TODO 这里是自己使用 volley 请求网络图片数据，不过使用 glid 加载图片不需要此步骤
//            RequestUtil.requestCoverImageListData(data)
        }

//        RequestUtil.listBeanData.observe(this@FragmentSinglePage, Observer{
//            mAdapter.data = it
//            mAdapter.notifyDataSetChanged()
//        })
    }

    override fun onStart() {
        super.onStart()
        when(position){
            0,3 -> view?.findViewById<Group>(R.id.sp_fragment_single_page_foot_group)?.visibility = View.VISIBLE
            1,2 -> view?.findViewById<Group>(R.id.sp_fragment_single_page_foot_group)?.visibility = View.INVISIBLE
            else ->{
                Log.i(TAG, "onStart: got wrong page at $position")
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.sp_fragment_single_page_button_next -> {
                if (maxPage != null){
                    if (page < maxPage!!){
                        mAdapter.data = null
                        mAdapter.notifyDataSetChanged()
                        page += 1
                        GlobalScope.launch(Dispatchers.Main) {
                            mAdapter.data = mViewModel.requestDataInPosition(position, page)
                            mAdapter.notifyDataSetChanged()
                            view?.findViewById<TextView>(R.id.sp_fragment_single_page_tips)?.text = "$page/$maxPage"
                        }
                    }
                }
            }
            R.id.sp_fragment_single_page_button_pre -> {
                if (maxPage != null){
                    if (page > 1 && page < maxPage!!){
                        mAdapter.data = null
                        mAdapter.notifyDataSetChanged()
                        page -= 1
                        GlobalScope.launch(Dispatchers.Main) {
                            mAdapter.data = mViewModel.requestDataInPosition(position, page)
                            mAdapter.notifyDataSetChanged()
                            view?.findViewById<TextView>(R.id.sp_fragment_single_page_tips)?.text = "$page/$maxPage"
                        }
                    }
                }
            }
        }
    }
}