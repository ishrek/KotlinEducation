package com.example.myapplication.commonKotlin.mvp.presenter

import android.content.Context

public class LoginPresenterImpl: LoginPresenter {
    private var context: Context? = null
    private var view: LoginPresenter.View? = null
    constructor (context: Context?, view: LoginPresenter.View?) {
        this.context = context
        this.view = view
    }

    override fun onLogin(username: String?, password: String?) {
        view ?: return
        view!!.onLoginPending()
        if (username == "1" && password == "1") {
            try {
                Thread.sleep(1500)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            view!!.onLoginSuccess()
        } else view!!.onLoginFail()
    }
}