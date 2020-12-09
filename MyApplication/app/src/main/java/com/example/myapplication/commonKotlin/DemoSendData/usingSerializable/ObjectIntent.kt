package com.example.myapplication.commonKotlin.DemoSendData.usingSerializable

import java.io.Serializable

class ObjectIntent : Serializable {
    val id: String = ""
    val name: String = ""
    var gender: String = "Male"
    val country: String = "Vietnam"
    val address: String? = null
}