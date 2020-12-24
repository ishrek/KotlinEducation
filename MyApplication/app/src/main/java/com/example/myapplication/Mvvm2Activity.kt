package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.commonKotlin.demoDataBinding.dataBindingRecycleView.DataModel
import com.example.myapplication.commonKotlin.demoDataBinding.dataBindingRecycleView.MyRecyclerViewAdapter
import com.example.myapplication.databinding.ActivityMvvm2Binding

//https://www.journaldev.com/23989/android-recyclerview-data-binding
class Mvvm2Activity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMvvm2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm2);
        populateData()
    }

    private fun populateData() {
        val dataModelList: MutableList<DataModel> = ArrayList()
        dataModelList.add(DataModel("Android Oreo", "8.1"))
        dataModelList.add(DataModel("Android Pie", "9.0"))
        dataModelList.add(DataModel("Android Nougat", "7.0"))
        dataModelList.add(DataModel("Android Marshmallow", "6.0"))
        val myRecyclerViewAdapter =
            MyRecyclerViewAdapter(dataModelList, this)
        mainBinding.myAdapter = myRecyclerViewAdapter
    }
}