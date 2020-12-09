package com.example.myapplication.commonKotlin.interfaceUtil

/*Interface
    - function not set final keyword
    - interface has properties and function ( non - body and have body )
    - can implement multi interface
    - have properties but do not init value to properties
    - do not constructor function
 */
interface MyInterface {

    val prop: Int // abstract
    val fullName: String
        get() = "$prop"
    val post: String
        get() = "Any post"

    fun makeVoice() // function not body

    val propertyWithImplementation: String
        get() = "foo"

    fun foo() {
        print("interface: prop = " + this.prop)
    }

    interface childOfMyInterface {
        val childItem: Int
    }
}