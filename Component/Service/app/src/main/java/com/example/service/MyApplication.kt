package com.example.service

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class MyApplication : Application() {
    companion object {
        const  val CHANNELD: String = "123"
    }
    override fun onCreate() {
        super.onCreate()
        creatChannelNotification()
    }

    private fun creatChannelNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            var Channel : NotificationChannel = NotificationChannel(CHANNELD, "Demo Service", NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager : NotificationManager = getSystemService(NOTIFICATION_SERVICE)  as NotificationManager

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(Channel)
            }
        }
    }

}