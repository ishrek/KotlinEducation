package com.example.myapplication.commonKotlin.demoDataBinding.retrofitWithRecycleView.adaper


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.commonKotlin.demoDataBinding.retrofitWithRecycleView.model.Employee
import com.example.myapplication.databinding.EmployeeListItemBinding


class EmployeeDataAdapter : RecyclerView.Adapter<EmployeeDataAdapter.EmployeeViewHolder>() {
    private var employees: ArrayList<Employee>? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): EmployeeViewHolder {
        val employeeListItemBinding: EmployeeListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            R.layout.employee_list_item, viewGroup, false
        )
        return EmployeeViewHolder(employeeListItemBinding)
    }

    override fun onBindViewHolder(employeeViewHolder: EmployeeViewHolder, i: Int) {
        val currentStudent = employees!![i]
        employeeViewHolder.employeeListItemBinding.employee = currentStudent
    }

    override fun getItemCount(): Int {
        return if (employees != null) {
            employees!!.size
        } else {
            0
        }
    }

    fun setEmployeeList(employees: ArrayList<Employee>?) {
        this.employees = employees
        notifyDataSetChanged()
    }

    inner class EmployeeViewHolder(employeeListItemBinding: EmployeeListItemBinding) :
        RecyclerView.ViewHolder(employeeListItemBinding.root) {
        val employeeListItemBinding: EmployeeListItemBinding = employeeListItemBinding
    }
}