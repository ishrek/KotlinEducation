package com.example.myapplication.commonKotlin.mvp2.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.myapplication.R
import com.example.myapplication.commonKotlin.mvp2.interfaces.IContact
import com.example.myapplication.commonKotlin.mvp2.presenters.ContactPresenter

class Mvp2Activity : AppCompatActivity(), IContact.View {

    private var presenter: ContactPresenter? = null

    @BindView(R.id.counterTextView)
    lateinit var counterTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp2)
        ButterKnife.bind(this)
        presenter = ContactPresenter(this)
    }

    override fun initView() {
        counterTextView.text = presenter?.getCounter()
    }

    @OnClick(R.id.clickBtn)
    fun onClickButton(){
        presenter?.incrementValue()
    }

    override fun updateViewData() {
        counterTextView.text = presenter?.getCounter()
    }
}