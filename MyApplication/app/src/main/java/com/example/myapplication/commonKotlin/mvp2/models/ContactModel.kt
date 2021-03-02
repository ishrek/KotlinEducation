package com.example.myapplication.commonKotlin.mvp2.models

import com.example.myapplication.commonKotlin.mvp2.interfaces.IContact

class ContactModel: IContact.Model {
    private var mCounter = 0

    override fun getCounter(): Int {
        return mCounter
    }

    override fun incrementCounter() {
        mCounter++
    }
}