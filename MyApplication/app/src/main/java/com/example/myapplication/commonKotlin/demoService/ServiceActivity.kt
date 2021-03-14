package com.example.myapplication.commonKotlin.demoService

import android.app.ActivityManager
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.commonKotlin.demoService.demoForgroundService.ForgroundService
import com.example.myapplication.commonKotlin.demoService.demoJobScheduler.DeepJobService
import com.example.myapplication.commonKotlin.demoService.demoJobScheduler.MyJobService

import kotlinx.android.synthetic.main.activity_service.*

class ServiceActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ServiceActivity"
        private const val SUCCESS_KEY = "SUCCESS"
        private const val FAILED_KEY = "FAILED"
        private const val JOB_ID = 123
        private const val PERIODIC_TIME: Long = 15 * 60 * 1000
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        // Variable to hold service class name
        val serviceClass = RandomNumberService::class.java

        // Initialize a new Intent instance
        val intent = Intent(applicationContext, serviceClass)


        // Button to start the service
        button_start.setOnClickListener{
            // If the service is not running then start it
            if (!isServiceRunning(serviceClass)) {
                // Start the service
                startService(intent)
            } else {
                toast("Service already running.")
            }
        }


        // Button to stop the service
        button_stop.setOnClickListener{
            // If the service is not running then start it
            if (isServiceRunning(serviceClass)) {
                // Stop the service
                stopService(intent)
            } else {
                toast("Service already stopped.")
            }
        }


        // Get the service status
        button_stats.setOnClickListener{
            if (isServiceRunning(serviceClass)) {
                toast("Service is running.")
            } else {
                toast("Service is stopped.")
            }

        }

        buttonStartService.setOnClickListener {
            startService()
        }
        buttonStopService.setOnClickListener{
            stopService()
        }

        buttonStartJobSchedule.setOnClickListener { scheduleJob() }
        buttonStopJobSchedule.setOnClickListener { cancelJob() }
    }

    private fun startService() {
        val serviceIntent = Intent(this, ForgroundService::class.java)
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android")
        ContextCompat.startForegroundService(this, serviceIntent)
    }

    private fun stopService() {
        val serviceIntent = Intent(this, ForgroundService::class.java)
        stopService(serviceIntent)
    }


    // Custom method to determine whether a service is running
    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        // Loop through the running services
        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                // If the service is running then return true
                return true
            }
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun scheduleJob() {
//        val componentName = ComponentName(this, MyJobService::class.java)
//        val info = JobInfo.Builder(JOB_ID, componentName)
//            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
//            .setRequiresDeviceIdle(false)
//            .setRequiresCharging(true)
//            .setPersisted(true)
//            .setPeriodic(PERIODIC_TIME)
//            .build()
//
//        val jobScheduler: JobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
//        val resultCode = jobScheduler.schedule(info)
//
//        val isJobScheduledSuccess = resultCode == JobScheduler.RESULT_SUCCESS
//        Log.d(TAG, "Job Scheduled ${if (isJobScheduledSuccess) SUCCESS_KEY else FAILED_KEY}")

        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val jobInfo = JobInfo.Builder(JOB_ID, ComponentName(this, DeepJobService::class.java))
        val job = jobInfo.setRequiresCharging(false)
            .setMinimumLatency(1)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setOverrideDeadline(PERIODIC_TIME).build()

        jobScheduler.schedule(job)

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun cancelJob() {
        val jobScheduler: JobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.cancel(JOB_ID)
        Log.d(TAG, "Job CANCELED")
    }
}



// Extension function to show toast message
fun Context.toast(message:String){
    Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
}