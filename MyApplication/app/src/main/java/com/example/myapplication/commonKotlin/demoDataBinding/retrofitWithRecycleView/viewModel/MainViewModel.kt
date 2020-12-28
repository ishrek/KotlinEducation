package com.example.myapplication.commonKotlin.demoDataBinding.retrofitWithRecycleView.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.commonKotlin.demoDataBinding.retrofitWithRecycleView.adaper.EmployeeRepository
import com.example.myapplication.commonKotlin.demoDataBinding.retrofitWithRecycleView.model.Employee


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val employeeRepository: EmployeeRepository = EmployeeRepository()
    val allEmployee: LiveData<List<Employee>?>
        get() = employeeRepository.getMutableLiveData()

}