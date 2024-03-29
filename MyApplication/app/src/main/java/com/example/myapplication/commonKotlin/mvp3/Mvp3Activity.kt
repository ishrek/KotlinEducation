package com.example.myapplication.commonKotlin.mvp3

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.commonKotlin.BaseClass.BaseActivity
import com.example.myapplication.commonKotlin.mvp3.interfaces.IContract
import com.example.myapplication.commonKotlin.mvp3.models.Model
import com.example.myapplication.commonKotlin.mvp3.presenters.Presenter
import com.google.android.material.snackbar.Snackbar

//https://www.geeksforgeeks.org/mvp-model-view-presenter-architecture-pattern-in-android-with-example/
class Mvp3Activity : BaseActivity(), IContract.View {
    private var textView: TextView? = null

    // creating object of Button class
    private var button: Button? = null

    // creating object of ProgressBar class
    private var progressBar: ProgressBar? = null

    // creating object of Presenter interface in Contract
    var presenter: Presenter? = null
    override fun getLayoutID(): Int {
        return R.layout.activity_mvp3
    }

    override fun onCreateActivity(savedInstanceState: Bundle?) {
        // assigning ID of the TextView
        textView = findViewById(R.id.textView)

        // assigning ID of the Button
        button = findViewById(R.id.button)

        // assigning ID of the ProgressBar
        progressBar = findViewById(R.id.progressBar)

        presenter = Presenter(this, Model())

        // operations to be performed when
        // user clicks the button
        presenter?.let {
            this.button!!.setOnClickListener(View.OnClickListener { presenter!!.onButtonClick() })
        }
    }

    override fun onDestroyActivity() {
        presenter?.let {
            it.onDestroy()
        }

        textView = null
        button = null
        progressBar  = null
        presenter  = null
    }

    override fun showProgress() {
        progressBar?.let {
            it.visibility = View.VISIBLE
        }

        textView?.let {
            it.visibility = View.INVISIBLE
        }
    }

    override fun hideProgress() {
        progressBar!!.visibility = View.GONE
        textView!!.visibility = View.VISIBLE
    }

    override fun setString(string: String?) {
        textView!!.text = string
    }
}

