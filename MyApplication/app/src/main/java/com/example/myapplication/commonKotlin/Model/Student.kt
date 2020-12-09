package com.example.myapplication.commonKotlin.Model

data class Student(
    val id: String,
    val name: String,
    val gender: String = "Male",
    val country: String = "Vietnam",
    val address: String? = null
)