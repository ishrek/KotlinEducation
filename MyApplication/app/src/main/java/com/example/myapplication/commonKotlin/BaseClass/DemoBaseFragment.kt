package com.example.myapplication.commonKotlin.BaseClass

import android.os.Bundle
import android.view.View
import com.example.myapplication.R


//https://viblo.asia/p/xay-dung-base-class-activity-fragment-3P0lPeN45ox
class DemoBaseFragment : BaseFragment() {
    override fun getLayoutID(): Int {
        return R.layout.fragment_demo_base
    }

    override fun onViewReady(view: View) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showSnackBar("Hello base fragment")
        var isNetwork = isNetworkConnected()
    }
}