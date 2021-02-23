package com.example.myapplication.commonKotlin.demoThread.demoParallel

import android.os.AsyncTask
import android.os.SystemClock
import android.widget.ProgressBar

class LoadingTask(progress: ProgressBar) : AsyncTask<Void, Int, Void>() {

    val mProgress: ProgressBar = progress

    override fun doInBackground(vararg voids: Void): Void? {
        for (i in 0..100) {
            publishProgress(i)
            SystemClock.sleep(1000)
        }
        return null
    }

    override fun onProgressUpdate(vararg values: Int?) {
        mProgress.progress = values[0]!!
    }

}