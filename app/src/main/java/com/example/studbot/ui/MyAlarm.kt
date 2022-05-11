package com.example.studbot.ui

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import com.example.studbot.R

class MyAlarm : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(
        context: Context,
        intent: Intent
    ) {
        var mp = MediaPlayer.create(context, R.raw.alarm)
        Log.d("Hello","repeating alarm")
        var notificationChannel = NotificationChannel("111","StudBot",NotificationManager.IMPORTANCE_HIGH)
        val buil = Notification.Builder(context,"111")
            .setSmallIcon(R.drawable.bot)
            .setContentTitle("Study Time")
            .setContentText("Wake up!")
            .setAutoCancel(true)

        val notificationManager= context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
        notificationManager.notify(123,buil.build())

        mp.start()


        //Log.d("Alarm Bell", "Alarm just fired")
    }
}