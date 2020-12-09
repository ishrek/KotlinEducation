package com.example.myapplication.commonKotlin

class SingletonKT private constructor() {

    private var legs: Int = 0

    companion object {
        val instance = SingletonKT()
    }

    init {
        legs = 4
    }

    fun getLegs(): Int {
        return legs
    }

    fun  setLegs(legs: Int){
        this.legs = legs
    }
}