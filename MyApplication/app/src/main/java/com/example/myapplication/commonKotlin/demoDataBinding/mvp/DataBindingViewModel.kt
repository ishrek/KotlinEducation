package com.example.myapplication.commonKotlin.demoDataBinding.mvp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataBindingViewModel: ViewModel() {
    /*
    * Using ObservableFields data
    *
    * https://developer.android.com/topic/libraries/data-binding/observability#observable_objects
    * */

    private val _name = MutableLiveData("")
    var age: Int = 0

    //LiveData is a lifecycle-aware observable so you need to specify what lifecycle owner to use. You do this in the binding object.
    val name: LiveData<String> = _name

    fun changeName() {
        _name.value = "Mikel Arteta"
    }
}