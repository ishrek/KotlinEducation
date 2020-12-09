package com.example.myapplication.commonKotlin.abstractClass
/*Abstract class
    - you could create final function
    - only extend a class
    - abstract have constructor function
*/
abstract class AbstractBase {
    abstract fun calculate()

    constructor(){
        print("constructor abstract class")
    }

    final fun addtraction(){
        print("final class")
    }

    fun substraction() {

    }

    abstract class chilAbtractClass {
        constructor(){
            print("constructor chilAbtractClass")
        }
    }
}
