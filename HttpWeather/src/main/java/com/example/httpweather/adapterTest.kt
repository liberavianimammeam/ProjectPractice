package com.example.httpweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.httpweather.util.GsonUtil
import com.google.gson.JsonArray
import org.json.JSONArray

class adapterTest(): RecyclerView.Adapter<viewholderTest>(){

    private var jsonArray: JSONArray? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholderTest {
        return viewholderTest(LayoutInflater.from(parent.context).inflate(R.layout.viewholder1, parent, false))
    }

    override fun onBindViewHolder(holder: viewholderTest, position: Int) {

        jsonArray?.let {
            jsonArray?.getJSONObject(position)?.let { it -> holder.bindNormal(GsonUtil.getLocationBean(it)) }
        }

    }

    override fun getItemCount(): Int {
        if (jsonArray == null){
            return 0;
        }else{
            return jsonArray!!.length()
        }
    }

    fun setJsonArray(jsonArray: JSONArray){
        this.jsonArray = jsonArray
    }
}