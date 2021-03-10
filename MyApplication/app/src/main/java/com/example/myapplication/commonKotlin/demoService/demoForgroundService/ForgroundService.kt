package com.example.myapplication.commonKotlin.demoService.demoForgroundService

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.myapplication.R
import com.example.myapplication.commonKotlin.demoService.ServiceActivity
import com.example.myapplication.commonKotlin.demoService.toast

/*
* Foreground service chạy sẽ bắt buộc phải đi kèm với 1 notification, và khi kill app đi service sẽ k bị kill theo
* */
class ForgroundService : Service() {
    val CHANNEL_ID = "ForegroundServiceChannel"

    override fun onCreate() {
        toast("ForgroundService created.")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        toast("ForgroundService onStartCommand.")
        val input = intent.getStringExtra("inputExtra")
        createNotificationChannel()
        val notificationIntent = Intent(this, ServiceActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.loading)
                .setContentIntent(pendingIntent)
                .build()
        startForeground(1, notification)
        //do heavy work on a background thread
        //stopSelf();
        return START_NOT_STICKY
    }


    override fun onDestroy() {
        toast("ForgroundService onDestroy.")
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        toast("ForgroundService createNotificationChannel.")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

}