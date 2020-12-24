package com.example.myapplication.commonKotlin.demoDataBinding.dataBindingRecycleView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.BR
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemRowBinding


class MyRecyclerViewAdapter(private val dataModelList: List<DataModel>, private val context: Context) :
    RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>(), CustomClickListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_row, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = dataModelList[position]
        holder.bind(dataModel)
        holder.itemRowBinding.itemClickListener = this
    }

    override fun getItemCount(): Int {
        return dataModelList.size
    }

    inner class ViewHolder(itemRowBinding: ItemRowBinding) : RecyclerView.ViewHolder(itemRowBinding.root) {
        var itemRowBinding: ItemRowBinding = itemRowBinding
        fun bind(obj: Any?) {
            itemRowBinding.setVariable(BR.model, obj)
            itemRowBinding.executePendingBindings()
        }
    }

    override fun cardClicked(f: DataModel?) {
        Toast.makeText(
            context, "You clicked " + f!!.androidName,
            Toast.LENGTH_LONG
        ).show()
    }

}