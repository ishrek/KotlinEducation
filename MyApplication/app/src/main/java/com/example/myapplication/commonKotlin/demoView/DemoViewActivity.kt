package com.example.myapplication.commonKotlin.demoView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.myapplication.R
import com.example.myapplication.commonKotlin.demoRetrofit.IWeatherService
import com.example.myapplication.commonKotlin.demoRetrofit.WeatherResponse
import com.example.myapplication.commonKotlin.demoRetrofit.retrofitV2.IwikiServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/*
* Demo layout
* https://hmkcode.com/android-layout-how-to-center-text-view-textview-horizontally-and-vertically/
*
* Retrofit
* https://www.c-sharpcorner.com/article/how-to-use-retrofit-2-with-android-using-kotlin/
*
* RxJava
* https://medium.com/mobile-app-development-publication/kotlin-and-retrofit-2-tutorial-with-working-codes-333a4422a890
* */

class DemoViewActivity : AppCompatActivity() {

    @BindView(R.id.label)
    lateinit var weatherData:TextView

    private val wikiApiServe by lazy {
        IwikiServices.create()
    }
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_view)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.btnLogin)
    fun getCurrentData() {
        val retrofit = Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(IWeatherService::class.java)
        val call = service.getCurrentWeatherData(lat, lon, AppId)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!

                    val stringBuilder = "Country: " +
                            weatherResponse.sys!!.country +
                            "\n" +
                            "Temperature: " +
                            weatherResponse.main!!.temp +
                            "\n" +
                            "Temperature(Min): " +
                            weatherResponse.main!!.temp_min +
                            "\n" +
                            "Temperature(Max): " +
                            weatherResponse.main!!.temp_max +
                            "\n" +
                            "Humidity: " +
                            weatherResponse.main!!.humidity +
                            "\n" +
                            "Pressure: " +
                            weatherResponse.main!!.pressure

                    weatherData!!.text = stringBuilder
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                weatherData!!.text = t.message
            }
        })
    }

    @OnClick(R.id.btnForgot)
    fun getGetWikiData(){
        beginSearch("abc")
    }

    private fun beginSearch(srsearch: String) {
        disposable =
            wikiApiServe.hitCountCheck("query", "json", "search", srsearch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> weatherData.text = "${result.query.searchinfo.totalhits}" },
                    { error -> weatherData.text = "${error.message}" }
                )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    companion object {
        var BaseUrl = "https://api.openweathermap.org/"
        var AppId = "d1c0d927bf4545a163c2a7fd897e2146"
        var lat = "35"
        var lon = "139"
    }
}