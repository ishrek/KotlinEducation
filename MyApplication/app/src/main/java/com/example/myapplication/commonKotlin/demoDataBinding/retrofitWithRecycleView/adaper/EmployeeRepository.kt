package com.example.myapplication.commonKotlin.demoDataBinding.retrofitWithRecycleView.adaper

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.commonKotlin.demoDataBinding.retrofitWithRecycleView.EmployeeDBResponse
import com.example.myapplication.commonKotlin.demoDataBinding.retrofitWithRecycleView.network.RetrofitClient.service
import com.example.myapplication.commonKotlin.demoDataBinding.retrofitWithRecycleView.model.Employee
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EmployeeRepository {
    private var employees: ArrayList<Employee>? = ArrayList()
    private val mutableLiveData =
        MutableLiveData<List<Employee>?>()

    fun getMutableLiveData(): MutableLiveData<List<Employee>?> {
        val userDataService = service
        userDataService.employees?.enqueue(object : Callback<EmployeeDBResponse?> {
            override fun onResponse(call: Call<EmployeeDBResponse?>?, response: Response<EmployeeDBResponse?>) {
                val employeeDBResponse: EmployeeDBResponse? = response.body()
                if (employeeDBResponse?.employee != null) {
                    employees = employeeDBResponse.employee as ArrayList<Employee>?
                    mutableLiveData.value = employees
                }
            }

            override fun onFailure(call: Call<EmployeeDBResponse?>?, t: Throwable?) {}
        })
        return mutableLiveData
    }

    companion object {
        private const val TAG = "EmployeeRepository"
    }
}