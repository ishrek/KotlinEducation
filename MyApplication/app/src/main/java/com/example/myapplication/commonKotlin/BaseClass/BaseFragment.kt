package com.example.myapplication.commonKotlin.BaseClass

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
    abstract fun getLayoutID(): Int
    abstract fun onViewReady(view: View)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayoutID(), container, false)
        onViewReady(view)
        return view
    }

    fun showToast(context: Context, message: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showToast(context, message)
        }
    }

    fun showSnackBar(message: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showSnackBar(message)
        }
    }

    fun isNetworkConnected(): Boolean {
        if (activity is BaseActivity) {
            return (activity as BaseActivity).isNetworkState
        }
        return false
    }
}