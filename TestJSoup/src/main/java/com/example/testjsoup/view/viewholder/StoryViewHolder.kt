package com.example.testjsoup.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_holder_activity_main.view.*

class StoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindView(text: String){
        itemView.view_holder_activity_main_text.text = text
    }

}