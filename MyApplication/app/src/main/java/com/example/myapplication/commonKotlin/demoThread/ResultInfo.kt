package com.example.myapplication.commonKotlin.demoThread

class ResultInfo(private val isCompleted: Boolean, private val workTimeInMillis: Long) {
    val message: String
        get() = if (isCompleted) {
            "Complete in $workTimeInMillis milliseconds"
        } else "Failed or cancelled"

}