package com.example.httpweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewholder1.view.*

class adapterTest(): RecyclerView.Adapter<viewholderTest>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholderTest {
        return viewholderTest(LayoutInflater.from(parent.context).inflate(R.layout.viewholder1, parent, false))
    }

    override fun onBindViewHolder(holder: viewholderTest, position: Int) {

        if(position == 0){
            holder.bindFirst()
        }else{
            holder.bindNormal()
        }

    }

    override fun getItemCount(): Int {
        return 20
    }
}