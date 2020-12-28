package com.example.myapplication.commonKotlin.demoDataBinding.retrofitWithRecycleView.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://reqres.in/api/"
    val service: EmployeeDataService
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(
                EmployeeDataService::class.java)
        }
}