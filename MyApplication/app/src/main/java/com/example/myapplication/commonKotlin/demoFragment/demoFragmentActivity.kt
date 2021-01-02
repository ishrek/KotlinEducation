package com.example.myapplication.commonKotlin.demoFragment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.myapplication.R


class demoFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_fragment)
        ButterKnife.bind(this)

        val parentVC_before = supportFragmentManager.fragments
        Log.d("ishrek", "getListFragments")
    }

    @OnClick(R.id.btn_addFragment)
    fun addNewFragmet() {
        //remove fm
        val  fm = supportFragmentManager
        val ft_add = fm.beginTransaction()
        ft_add.add(R.id.frame_layout, ThreeFragment())
        ft_add.commit()
        val parentVC_before = supportFragmentManager.fragments
        Log.d("ishrek", "getListFragments")
    }
}