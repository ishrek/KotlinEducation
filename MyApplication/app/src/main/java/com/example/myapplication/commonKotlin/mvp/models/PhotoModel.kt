package com.example.myapplication.commonKotlin.mvp.models

class PhotoModel {
    constructor (link: String, name: String, des: String) {
        this.link = link
        this.name = name
        this.des = des
    }

    var link: String = ""
    var name: String = ""
    var des: String = ""
}