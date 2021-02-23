package com.example.myapplication.commonKotlin.demoThread

import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.myapplication.R
import com.example.myapplication.commonKotlin.demoThread.demoParallel.LoadingTask
import java.util.*

/*
*  https://viblo.asia/p/asynctask-va-nhung-dieu-co-the-ban-chua-biet-YWOZrwMvlQ0
* https://viblo.asia/p/thread-handler-va-asynctask-trong-android-3P0lPyp85ox
* https://niithanoi.edu.vn/tat-tan-tat-ve-queue-trong-java.html
* */
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

    @BindView(R.id.progressBar1)
    lateinit var progressBar1: ProgressBar

    @BindView(R.id.progressBar2)
    lateinit var progressBar2: ProgressBar

    @BindView(R.id.progressBar3)
    lateinit var progressBar3: ProgressBar

    @BindView(R.id.progressBar4)
    lateinit var progressBar4: ProgressBar

    @BindView(R.id.progressBar5)
    lateinit var progressBar5: ProgressBar

    @BindView(R.id.button_start_parallel)
    lateinit var startParallel: Button

    lateinit var mAsyncFirst: LoadingTask
    lateinit var mAsyncSecond: LoadingTask
    lateinit var mAsyncThird: LoadingTask
    lateinit var mAsyncFourth: LoadingTask
    lateinit var mAsyncFifth: LoadingTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asyn_task)
        ButterKnife.bind(this)

        mAsyncFirst = LoadingTask(progressBar1)
        mAsyncSecond = LoadingTask(progressBar2)
        mAsyncThird = LoadingTask(progressBar3)
        mAsyncFourth = LoadingTask(progressBar4)
        mAsyncFifth = LoadingTask(progressBar5)

        demoQueue()
    }

    private fun demoQueue() {
        // Tạo Queue sử dụng class LinkedList
        // Tạo Queue sử dụng class LinkedList
        val numbers: Queue<Int> = LinkedList()

        // Chèn phần tử chỉ định vào Queue bằng phương thức offer
        // Chèn phần tử chỉ định vào Queue bằng phương thức offer
        numbers.offer(1)
        numbers.offer(2)
        numbers.offer(3)
        println("Queue: $numbers")

        // Truy cập các phần tử trong Queue

        // Truy cập các phần tử trong Queue
        val accessedNumber = numbers.peek()
        println("Truy cập phần tử: $accessedNumber")

        // Loại bỏ các phần tử tron Queue
        // Loại bỏ các phần tử tron Queue
        val removedNumber = numbers.poll()
        println("Loại bỏ phần tử: $removedNumber")

        println("Cập nhật Queue: $numbers")
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

    @OnClick(R.id.button_start_parallel)
    fun requestParallel() {

        if(mAsyncFirst.getStatus() != AsyncTask.Status.RUNNING){
            // My AsyncTask is currently doing work in doInBackground()
            mAsyncFirst.execute()
        }

        if(mAsyncThird.getStatus() != AsyncTask.Status.RUNNING){
            // My AsyncTask is currently doing work in doInBackground()
            mAsyncThird.execute()
        }

        if(mAsyncSecond.getStatus() != AsyncTask.Status.RUNNING){
            // My AsyncTask is currently doing work in doInBackground()
            mAsyncSecond.execute()
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if(mAsyncFourth.getStatus() != AsyncTask.Status.RUNNING){
                // My AsyncTask is currently doing work in doInBackground()
                mAsyncFourth.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            }

            if(mAsyncFifth.getStatus() != AsyncTask.Status.RUNNING){
                // My AsyncTask is currently doing work in doInBackground()
                mAsyncFifth.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            }
        } else {

            if(mAsyncFourth.getStatus() != AsyncTask.Status.RUNNING){
                // My AsyncTask is currently doing work in doInBackground()
                mAsyncFourth.execute()
            }

            if(mAsyncFifth.getStatus() != AsyncTask.Status.RUNNING){
                // My AsyncTask is currently doing work in doInBackground()
                mAsyncFifth.execute()
            }
        }
    }

}