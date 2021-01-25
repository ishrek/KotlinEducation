package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.commonKotlin.DemoSendData.usingParcelable.PhieuChi
import com.example.myapplication.commonKotlin.DemoSendData.usingSerializable.ObjectIntent
import com.example.myapplication.commonKotlin.Utils.EXTRA_MESSAGE
import com.example.myapplication.commonKotlin.demoDataBinding.retrofitWithRecycleView.adaper.EmployeeDataAdapter
import com.example.myapplication.commonKotlin.demoDataBinding.retrofitWithRecycleView.model.Employee
import com.example.myapplication.commonKotlin.demoDataBinding.retrofitWithRecycleView.viewModel.MainViewModel
import com.example.myapplication.databinding.ActivityLoginBinding

//https://androidwave.com/android-data-binding-recyclerview/
class LoginActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var employeeDataAdapter: EmployeeDataAdapter
    private lateinit var loginBinding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        // bind RecyclerView
        val recyclerView: RecyclerView = loginBinding.viewEmployees
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        employeeDataAdapter = EmployeeDataAdapter()
        recyclerView.adapter = employeeDataAdapter
        getAllEmployee()


        val message =  intent.getStringExtra(EXTRA_MESSAGE())
        val  obj = intent.extras?.get("extra_object") as ObjectIntent
        Log.d("Shrek", message)
        Log.d("Shrek", obj.gender)

        //get obj from intent via parcelable
        val args = intent.getBundleExtra("MY_PARAMS")
        val pc = args.getParcelable<PhieuChi>("PHIEU_CHI")
        if (pc != null) {
            Log.d("Shrek", pc.ngayGio)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Log.d("Shrek", "onOptionsItemSelected")
                finish()
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d("Shrek", "onBackPressed")
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
    }

    private fun getAllEmployee() {
        mainViewModel.allEmployee.observe(
            this,
            Observer<List<Employee?>?> { employees ->
                employeeDataAdapter.setEmployeeList(employees as ArrayList<Employee>)
            })
    }
}