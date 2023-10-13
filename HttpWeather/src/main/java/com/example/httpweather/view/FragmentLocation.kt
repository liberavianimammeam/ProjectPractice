package com.example.httpweather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.httpweather.R
import com.example.httpweather.adapterTest
import com.example.httpweather.bean.LocationBean
import com.example.httpweather.databinding.LocationsBinding
import com.example.httpweather.util.GsonUtil
import com.example.httpweather.view.adapter.LocationAdapter
import com.example.httpweather.viewModel.FragmentLocationViewModel
import com.google.gson.Gson

class FragmentLocation: Fragment() {

    private lateinit var binding: LocationsBinding
    private val mViewModel: FragmentLocationViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(FragmentLocationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.locations, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LocationsBinding.inflate(layoutInflater)
        var mAdapter = LocationAdapter(mViewModel, this)
        binding.locationsRecyclerView.adapter = mAdapter
        binding.locationsRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        binding.locationsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mViewModel.resolveJsonData(requireContext())
        mViewModel.locationDetail.observe(requireActivity(), Observer {
            mAdapter.mLocationData = it
            mAdapter.notifyDataSetChanged()
        })

    }

}