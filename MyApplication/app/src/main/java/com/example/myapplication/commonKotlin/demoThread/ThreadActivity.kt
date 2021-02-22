package com.example.myapplication.commonKotlin.demoThread

import android.os.Bundle
import android.os.SystemClock
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.myapplication.R


class ThreadActivity : AppCompatActivity() {

    @BindView(R.id.progressBar1)
    lateinit var progressBar: ProgressBar

    @BindView(R.id.tvProgressBar)
    lateinit var tvProgressBar : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.startProgress)
    fun startProgress() {
        // do something long
        val runnable = Runnable {
            for (i in 0..10) {
                doFakeWork()
                progressBar.post {
                    progressBar.progress = i
                    val percent = i % 10
                    tvProgressBar.text = "Updating $percent%"
                    if (i == 10)
                        tvProgressBar.text = "Finished"
                }
            }
        }
        Thread(runnable).start()
    }

    // Simulating something timeconsuming
    private fun doFakeWork() {
        SystemClock.sleep(2000)
    }
}