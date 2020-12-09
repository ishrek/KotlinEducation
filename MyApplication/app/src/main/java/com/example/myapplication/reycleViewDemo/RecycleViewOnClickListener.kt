package com.example.myapplication.reycleViewDemo

import android.view.View

interface RecycleViewOnClickListener {
    fun onDidSelected()
    fun didSelectedItem(position: Int)
}