package com.example.myapplication.commonKotlin.mvp3.presenters

import com.example.myapplication.commonKotlin.mvp3.interfaces.IContract

class Presenter(var mainView: IContract.View?, val model: IContract.Model) : IContract.Presenter, IContract.Model.OnFinishedListener {
    override fun onButtonClick() {
        mainView?.let {
            it.showProgress()
        }
        model.getNextCourse(this)
    }

    override fun onDestroy() {
        mainView = null
    }

    override fun onFinished(string: String?) {
        mainView?.let {
            it.setString(string)
            it.hideProgress()
        }
    }
}