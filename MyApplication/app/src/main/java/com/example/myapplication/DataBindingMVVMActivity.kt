package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.commonKotlin.demoDataBinding.mvvm.MVVMLoginViewModel
import com.example.myapplication.commonKotlin.demoDataBinding.mvvm.UserObject
import com.example.myapplication.databinding.ActivityDataBindingMVVMBinding

class DataBindingMVVMActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityDataBindingMVVMBinding
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MVVMLoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
    }

    private fun initBinding() {
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_m_v_v_m)
        mainBinding.lifecycleOwner = this
        mainBinding.viewModel = viewModel

        viewModel.user.observe(this, Observer<UserObject> { user ->
            Log.d("iSHrek", "Observer")
            if (user.email.isNotEmpty() || user.password.isNotEmpty())
                Toast.makeText(
                    applicationContext,
                    "email : " + user.email + " password " + user.password,
                    Toast.LENGTH_SHORT
                ).show()
        })
    }
}