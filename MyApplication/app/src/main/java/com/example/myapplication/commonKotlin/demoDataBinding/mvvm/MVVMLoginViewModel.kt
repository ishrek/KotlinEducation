package com.example.myapplication.commonKotlin.demoDataBinding.mvvm

import android.os.Handler
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//https://www.journaldev.com/22561/android-mvvm-livedata-data-binding
class MVVMLoginViewModel : ViewModel() {
    var errorPassword = MutableLiveData<String?>()
    var errorEmail = MutableLiveData<String?>()
    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    private val _busy = MutableLiveData(View.GONE)
    val busy: LiveData<Int> = _busy

    private var userMutableLiveData = MutableLiveData<UserObject>()
    val user: MutableLiveData<UserObject>
        get() = userMutableLiveData

    fun onLoginClicked() {
        Log.d("iSHrek", "onLoginClicked")
        _busy.value = View.VISIBLE
        Handler().postDelayed(Runnable {
            val user = UserObject(email.value, password.value)
            if (!user.isEmailValid) {
                errorEmail.setValue("Enter a valid email address")
            } else {
                errorEmail.setValue(null)
            }
            if (!user.isPasswordLengthGreaterThan5) errorPassword.setValue("Password Length should be greater than 5") else {
                errorPassword.setValue(null)
            }
            userMutableLiveData!!.value = user
            _busy.value = View.GONE
            View.GONE
        }, 3000)
    }
}