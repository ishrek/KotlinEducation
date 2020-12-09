package com.example.myapplication.commonKotlin.mvp.presenter

import android.content.Context
import com.example.myapplication.commonKotlin.mvp.models.PhotoModel

public interface MainActivityPresenter {
    fun init()
    fun login(username: String, password:String, completion: (Boolean) -> Unit)
    interface View {
        fun onLoginPending()
        fun onLoginSuccess()
        fun onLoginFail()
    }
}