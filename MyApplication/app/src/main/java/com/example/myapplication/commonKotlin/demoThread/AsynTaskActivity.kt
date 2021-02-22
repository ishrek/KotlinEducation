package com.example.myapplication.commonKotlin.demoThread

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.myapplication.R


class AsynTaskActivity : AppCompatActivity() {

    private var myAsyncTask: MyAsyncTask? = null

    @BindView(R.id.progressBarDownload)
    lateinit var progressBar: ProgressBar

    @BindView(R.id.textView_info)
    lateinit var textViewInfo: TextView

    @BindView(R.id.button_start)
    lateinit var buttonStart: Button

    @BindView(R.id.button_cancel)
    lateinit var buttonCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asyn_task)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.button_start)
    fun startWork() {
        myAsyncTask = MyAsyncTask(
            progressBar,
            textViewInfo, buttonStart, buttonCancel
        )
        val param = ParamInfo("Param 1", "Param 2")
        myAsyncTask!!.execute(param)
    }

    @OnClick(R.id.button_cancel)
    fun requestCancel() {
        if (myAsyncTask != null) {
            myAsyncTask!!.cancel(true)
        }
    }

}