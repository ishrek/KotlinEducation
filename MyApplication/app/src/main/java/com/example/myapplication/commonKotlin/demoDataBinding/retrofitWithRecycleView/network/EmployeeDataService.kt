package com.example.myapplication.commonKotlin.demoDataBinding.retrofitWithRecycleView.network

import com.example.myapplication.commonKotlin.demoDataBinding.retrofitWithRecycleView.EmployeeDBResponse
import retrofit2.Call
import retrofit2.http.GET


interface EmployeeDataService {
    @get:GET("users/?per_page=12&page=1")
    val employees: Call<EmployeeDBResponse?>?
}