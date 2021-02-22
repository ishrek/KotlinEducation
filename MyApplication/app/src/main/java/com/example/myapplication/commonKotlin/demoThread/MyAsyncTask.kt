package com.example.myapplication.commonKotlin.demoThread

import android.os.AsyncTask
import android.os.SystemClock
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.util.*

// <Params, Progress, Result>
class MyAsyncTask(
    private val progressBar: ProgressBar, private val textViewInfo: TextView,
    buttonStart: Button, buttonCancel: Button
) : AsyncTask<ParamInfo?, ProgressInfo?, ResultInfo>() {
    private val buttonStart: Button = buttonStart
    private val buttonCancel: Button = buttonCancel
    private val PROGRESS_MAX: Int = progressBar.max
    private var workCount = 0
    private var startTimeInMillis: Long = 0
    override fun onPreExecute() {
        progressBar.visibility = ProgressBar.VISIBLE
        textViewInfo.text = "Start..."
        buttonStart.isEnabled = false
        buttonCancel.isEnabled = true
        startTimeInMillis = Date().time
    }

    override fun onProgressUpdate(vararg values: ProgressInfo?) {
        super.onProgressUpdate(*values)
        val progressInfo: ProgressInfo = values[0]!!
        val progress: Int = progressInfo.progress
        progressBar.progress = progress
        textViewInfo.text = progressInfo.workingInfo
    }

    override fun onPostExecute(resultInfo: ResultInfo) {
        super.onPostExecute(resultInfo)
        buttonStart.isEnabled = true
        buttonCancel.isEnabled = false
        textViewInfo.text = resultInfo.message
    }

    override fun onCancelled(resultInfo: ResultInfo) {
        super.onCancelled(resultInfo)
        buttonStart.isEnabled = true
        buttonCancel.isEnabled = false
        textViewInfo.text = resultInfo.message
    }

    override fun doInBackground(vararg params: ParamInfo?): ResultInfo {
        val WORK_MAX = 30
        while (workCount < WORK_MAX) {
            SystemClock.sleep(100) // 100 Milliseconds.
            workCount++
            val progress = workCount * PROGRESS_MAX / WORK_MAX // Progress value.
            val percent = progress * 100 / PROGRESS_MAX
            val info =
                "($percent%) - Working part $workCount of $WORK_MAX"
            val progressInfo = ProgressInfo(progress, info)
            publishProgress(progressInfo) // Progress ...values
        }
        val finishTimeInMillis: Long = Date().time
        val workTimeInMillis = finishTimeInMillis - startTimeInMillis
        return ResultInfo(true, workTimeInMillis)
    }
}
