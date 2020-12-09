package com.example.myapplication.reycleViewDemo

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.myapplication.R
import kotlinx.android.synthetic.main.header_layout.view.*

class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    @BindView(R.id.header_id)
    lateinit var textView: TextView

    init {
        ButterKnife.bind(this, view)
    }

    fun bindData(title: String){
        this.textView.text = title
    }
}