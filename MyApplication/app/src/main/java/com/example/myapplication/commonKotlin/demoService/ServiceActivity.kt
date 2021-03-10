package com.example.myapplication.commonKotlin.demoService

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.commonKotlin.demoService.demoForgroundService.ForgroundService

import kotlinx.android.synthetic.main.activity_service.*

class ServiceActivity : AppCompatActivity() {
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
}



// Extension function to show toast message
fun Context.toast(message:String){
    Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
}