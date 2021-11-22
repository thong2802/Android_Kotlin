package com.example.startedservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class StartedService : Service() {
    companion object{
         val TAG : String? = StartedService::class.simpleName
    }
    override fun onBind(intent: Intent?): IBinder? {
       return null
    }
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate run on :" +  Thread.currentThread().name)
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand run on :" +  Thread.currentThread().name)
        stopSelf()
       // return super.onStartCommand(intent, flags, startId)
        return  START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy run on :" +  Thread.currentThread().name)
    }
}