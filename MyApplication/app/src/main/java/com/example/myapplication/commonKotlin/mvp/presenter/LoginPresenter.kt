package com.example.myapplication.commonKotlin.mvp.presenter

public interface LoginPresenter {
    fun onLogin(username: String?, password: String?)
    interface View {
        fun onLoginPending()
        fun onLoginSuccess()
        fun onLoginFail()
        fun onStartProcessBar(message: String?)
        fun onStopProcessBar()
        fun requestLogin(username: String?, password: String?)
    }
}