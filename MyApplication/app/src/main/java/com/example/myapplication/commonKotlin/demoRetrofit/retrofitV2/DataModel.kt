package com.example.myapplication.commonKotlin.demoRetrofit.retrofitV2

object DataModel {
    data class Result(val query: Query)
    data class Query(val searchinfo: SearchInfo)
    data class SearchInfo(val totalhits: Int)


}