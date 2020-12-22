package com.example.myapplication.commonKotlin.demoDataBinding.mvvm

import android.util.Patterns


class UserObject(private val mEmail: String?, private val mPassword: String?) {
    val email: String
        get() = mEmail ?: ""

    val password: String
        get() = mPassword ?: ""

    val isEmailValid: Boolean
        get() = Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()

    val isPasswordLengthGreaterThan5: Boolean
        get() = mPassword!!.length > 5

}