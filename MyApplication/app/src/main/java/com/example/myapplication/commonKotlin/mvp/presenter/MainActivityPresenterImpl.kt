package com.example.myapplication.commonKotlin.mvp.presenter

import android.content.Context
import android.util.Log
import com.example.myapplication.commonKotlin.mvp.interfaces.PhotoRepository
import com.example.myapplication.commonKotlin.mvp.interfaces.PhotoRepositoryImpl

public class MainActivityPresenterImpl: MainActivityPresenter, MainActivityPresenter.View{
    private var context: Context? = null
    private var repository: PhotoRepository? = null
    private var view: MainActivityPresenter.View? = null
    constructor (context: Context?) {
        this.context = context
        repository = PhotoRepositoryImpl(context)
//        this.view = view
    }
    override fun init() {
        println(repository?.getPhotos())
    }

    override fun login(username: String, password: String, completion: (Boolean) -> Unit) {
        onLoginPending()
        repository?.getListPhotos(username)?.let {
            if (it) {
                onLoginSuccess()
            } else
                onLoginFail()
            completion(it)
        }
    }

    override fun onLoginPending() {
        Log.e("Shrek", "onLoginPending")
    }

    override fun onLoginFail() {
        Log.e("Shrek", "onLoginFail")
    }

    override fun onLoginSuccess() {
        Log.e("Shrek", "onLoginSuccess")
    }
}