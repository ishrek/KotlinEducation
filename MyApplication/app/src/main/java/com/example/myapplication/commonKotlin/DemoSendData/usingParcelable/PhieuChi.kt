package com.example.myapplication.commonKotlin.DemoSendData.usingParcelable

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PhieuChi() : Parcelable {
    var ngayGio: String = ""
    var lyDoChi: String = ""
    // construtor 1
    constructor(ngayGio: String, lyDoChi: String) : this() {
        this.ngayGio = ngayGio
        this.lyDoChi = lyDoChi
    }

    // construtor 2
    constructor(ngayGio: String) : this(){
        this.ngayGio = ngayGio
    }
}