package com.example.myapplication.commonKotlin.delegationUtil

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class TrimDelegation : ReadWriteProperty<Any?, String> {

    private var trimmedValue: String = ""

    override fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): String {
        return trimmedValue
    }

    override fun setValue(
        thisRef: Any?,
        property: KProperty<*>,
        value: String
    ) {
        trimmedValue = value.trim()
    }
}