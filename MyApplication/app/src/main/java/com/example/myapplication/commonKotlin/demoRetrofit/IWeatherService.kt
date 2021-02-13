package com.example.myapplication.commonKotlin.demoRetrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherService {
    @GET("data/2.5/weather?")
    fun getCurrentWeatherData(@Query("lat") lat: String, @Query("lon") lon: String, @Query("APPID") app_id: String): Call<WeatherResponse>

    @GET("users") //fake api
    fun getUsers(): Call<List<WeatherResponse>>

}