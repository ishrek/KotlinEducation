package com.example.myapplication.commonKotlin.mvp2.presenters

import com.example.myapplication.commonKotlin.mvp2.interfaces.IContact.*
import com.example.myapplication.commonKotlin.mvp2.models.ContactModel

class ContactPresenter(_view: View): Presenter {

    private var view: View = _view
    private var model: Model = ContactModel()

    init {
        view.initView()
    }

    override fun incrementValue() {
        model.incrementCounter()
        view.updateViewData()
    }

    override fun getCounter(): String {
        return model.getCounter().toString()
    }
}