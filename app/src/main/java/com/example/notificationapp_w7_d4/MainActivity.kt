package com.example.notificationapp_w7_d4

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.notificationapp_w7_d4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeBinding()
        makeNotifications()
    }


    private fun makeNotifications(){
        var builder: Notification.Builder
        val notificationId = 1234
        val channelId = "myapp.notifications"
        val description = "Notification App Example"

        var notificationManager: NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        binding.button.setOnClickListener{
            val text = binding.etMain.text.toString()
            binding.etMain.text.clear()
            binding.etMain.clearFocus()
            builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(notificationChannel)
                Notification.Builder(this, channelId).setSmallIcon(R.drawable.icon)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.icon))
                    .setContentTitle("New Notification").setContentText(text)
            } else {
                Notification.Builder(this).setSmallIcon(R.drawable.icon)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.icon))
                    .setContentTitle("even more New Notification").setContentText(text)
            }
            notificationManager.notify(notificationId, builder.build())
        }
    }

    private lateinit var binding: ActivityMainBinding
    private fun initializeBinding() {
        Log.d("MAIN", "Binding Initialized!")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}