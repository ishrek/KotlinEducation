package com.example.myapplication.commonKotlin.demoService.demoJobScheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MyJobService: JobService() {

    companion object {
        private const val TAG = "MyJobService"
        private const val TIME_SLEEP_MILLISECONDS: Long = 1000
    }

    private var jobCanceled : Boolean = false

    private fun doBackgroundWork(p0: JobParameters?) {
        Log.d(TAG, "doBackgroundWork")
        Thread(Runnable {
            kotlin.run {
                for (i: Int in 0 until 9) {
                    Log.d(TAG, "run: $i")

                    if (jobCanceled) {
                        return@run
                    }

                    Thread.sleep(TIME_SLEEP_MILLISECONDS)
                }
                Log.d(TAG, "Job finish")
                jobFinished(p0, false)
            }
        }).start()
    }

    override fun onStartJob(p0: JobParameters?): Boolean {
        Log.d(TAG, "Job Started")
        doBackgroundWork(p0)
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.d(TAG, "Job canceled before completion")
        jobCanceled = true
        return true
    }
}