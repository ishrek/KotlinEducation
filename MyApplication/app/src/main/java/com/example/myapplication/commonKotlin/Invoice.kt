package com.example.myapplication.commonKotlin

import com.example.myapplication.commonKotlin.abstractClass.AbstractBase

//Primary constructor
class Invoice(id: Int, var firstName: String) {
    var numberId: Int = id

    //init block run behind primary constructor
    init {
        println("Customer's name is $id")
        println("Customer's name is $numberId")
    }

    //secondary constructor
    constructor(id: Int, firstName: String, age: Int) : this(id, firstName) {

    }
}

//Secondary Constructor
class CustomerExtendClass : AbstractBase {
    var id: Int = 0
    var name: String = ""

    constructor(id: Int) {
        //code
    }

    constructor(name: String, id: Int) {
        this.name = name
        this.id = id
    }

    override fun calculate() {
        println("abstract class")
    }

    fun callBackFunction(completion: (String) -> Unit) {
        val demo: String = "Callback function demo"
        completion(demo)
    }

    fun callBackFunction2(completion: (String, Array<String>) -> Unit) {
        val demo: String = "Callback function demo"
        completion(demo, arrayOf("Abu", "Praveen", "Sathya", "Yogesh", "Ram"))
    }
}

class Derparment(id: Int) : IPerson("Miyatsu") {
    var name: String = ""

    override fun getHeight() {
        super.getHeight()
    }

    override fun getLocation() {
        super.getLocation()
        println("HCM")
    }
}

//Interface
open class IPerson(company: String) {
    var company = company
    open fun getHeight() {}
    open fun getLocation() {
        println("Hanoi $company")
    }
}

