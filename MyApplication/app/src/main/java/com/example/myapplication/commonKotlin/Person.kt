package com.example.myapplication.commonKotlin

import com.example.myapplication.commonKotlin.interfaceUtil.MyInterface

open class Base {
    open fun v() {}
    open fun a() {}
    fun nv() {}
}

open class Person : Base {
    lateinit var name: String

    init {
        println("Person init blocks")
    }

    constructor(name: String){
        println("Person constructor")
        this.name = name
    }

    override fun v() {
        super.v()
        println("override fun")
    }

    final override fun a() {
        super.a()
    }
}

class Child: Person("arsenal") {
    override fun v() {
        super.v()
    }
}

class Child2: MyInterface {
    override val prop: Int = 29
    override fun makeVoice() {
        TODO("Not yet implemented")
    }

    fun greaterthanTen(x: Int): Boolean {
        return x > 10
    }
}