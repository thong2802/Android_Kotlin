package com.example.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.service.MyApplication.Companion.CHANNELD
import java.util.logging.Logger


class Service : Service(){
    override fun onCreate() {
        super.onCreate()
        Log.e("Thong", " Myservice create")
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null;
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val strDataIntent : String? = intent!!.getStringExtra("key_data_intent")
        sendNotification(strDataIntent)
        return Service.START_NOT_STICKY
    }

    private fun sendNotification(strDataIntent: String?) {
        val intent : Intent = Intent(this, MainActivity::class.java)
        val pendingIntent : PendingIntent? = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        var builder = NotificationCompat.Builder(this, CHANNELD)
            .setContentTitle("Demo")
            .setContentText(strDataIntent)
            .setContentIntent(pendingIntent)
            .build()
            startForeground(1, builder)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Thong", " Myservice destroy")
    }

}