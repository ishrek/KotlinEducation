package com.example.myapplication.reycleViewDemo

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class RecycleViewAdapter(
    private var moviesList: List<MovieModel>,
    private val recycleViewClickListener: RecycleViewOnClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER) {
            val headerView = parent.inflate(R.layout.header_layout, false)
            HeaderViewHolder(headerView)
        } else {
            val rowView = parent.inflate(R.layout.item_recycle_view, false)
            ItemViewHolder(rowView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = moviesList[position]
        if (holder is ItemViewHolder) {
            holder.bindPhoto(movie)
            holder.saveBtn.setOnClickListener{
                recycleViewClickListener.onDidSelected()
            }

            holder.getPositionBtn.setOnClickListener {
                recycleViewClickListener.didSelectedItem(position)
            }
        }

        if (holder is HeaderViewHolder) {
            holder.bindData("Huong PSH")
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    //Set type item for index
    private fun isPositionHeader(position: Int): Boolean {
        return position == 0 || position == 10
    }

    //Data source
    override fun getItemCount(): Int {
        return moviesList.size
    }
}